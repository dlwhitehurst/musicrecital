/**
 * Copyright 2014 David L. Whitehurst
 * 
 * Licensed under the Apache License, Version 2.0 
 * (the "License"); You may not use this file except 
 * in compliance with the License. You may obtain a 
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific 
 * language governing permissions and limitations under the 
 * License.
 * 
 */

package org.musicrecital.webapp.pages;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.services.Response;
import org.musicrecital.Constants;
import org.musicrecital.model.User;
import org.musicrecital.service.RoleManager;
import org.musicrecital.service.UserExistsException;
import org.musicrecital.service.UserManager;
import org.musicrecital.webapp.components.UserForm;
import org.musicrecital.webapp.services.EmailService;
import org.musicrecital.webapp.services.SecurityContext;
import org.musicrecital.webapp.util.RequestUtil;
import org.slf4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Self-registration page for new users
 *
 * @author Serge Eby
 * @version $Id$
 */
public class Signup {
    @Inject
    private Logger logger;

    @Inject
    private UserManager userManager;

    @Inject
    private RoleManager roleManager;

    @Inject
    private AlertManager alertManager;

    @Inject
    private EmailService emailService;

    @Property
    @PageActivationContext
    private User user;

    @Inject
    private HttpServletRequest request;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private Response response;

    @Inject
    private Messages messages;

    @Component(id = "signup")
    private UserForm form;

    @Property
    private Boolean cookieLogin;

    void onPrepare() {
        if (user == null) {
            user = new User();
        }
        // Enable user;
        user.setEnabled(true);

        // Set the default user role on this new user
        user.addRole(roleManager.getRole(Constants.USER_ROLE));
    }

    void setupRender() {
        form.setInfoMessage(messages.get("signup.message"));
    }

    // ~ Event Handlers

    @Log
    Object onCanceledFromSignup() {
        return Login.class;
    }

    @Log
    void onValidatePasswordFromSignup() {
        // Ensure the password fields match
        if (form.isValid()) {
            if (!StringUtils.equals(user.getPassword(), user.getConfirmPassword())) {

                String errorMessage = messages.format("errors.twofields",
                        messages.get("user.confirmPassword"),
                        messages.get("user.password"));

                form.recordError(errorMessage);

                alertManager.alert(
                        Duration.TRANSIENT,
                        Severity.ERROR,
                        errorMessage);
            }
        }
    }

    @Log
    Object onSuccess() throws UserExistsException, IOException {
        try {
            user = userManager.saveUser(user);
        } catch (AccessDeniedException ade) {
            // thrown by UserSecurityAdvice configured in aop:advisor
            logger.warn(ade.getMessage());
            return new HttpError(HttpServletResponse.SC_FORBIDDEN, "Resource not available");
        } catch (UserExistsException e) {
            // TODO #1: FIXME: only username should be highlighted.. move to onValidate()?

            alertManager.error(
                    messages.format("errors.existing.user", user.getUsername(), user.getEmail())
            );
            // redisplay the unencrypted passwords
            user.setPassword(user.getConfirmPassword());
            //TODO: somehow returning current page doesn't work
            //return this;

            response.sendRedirect("signup");
            return null;
        }

        // log user in automatically
        securityContext.login(user);

        // Send user an e-mail
        logger.debug(String.format("Sending user '%s' an account information e-mail", user.getUsername()));
        try {
            String msg = messages.get("signup.email.message");
            String subject = messages.get("signup.email.subject");
            emailService.send(user, subject, msg, RequestUtil.getAppURL(request), false);
        } catch (MailException me) {
            request.getSession(true).setAttribute("error",
                    me.getMostSpecificCause().getMessage());
        }

        alertManager.alert(Duration.TRANSIENT, Severity.INFO,  messages.get("user.registered"));
        return Home.class;
    }

    @Log
    void onFailure() throws IOException {
        response.sendRedirect("signup");
    }
}
