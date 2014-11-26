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

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.musicrecital.model.User;
import org.musicrecital.service.UserManager;
import org.musicrecital.webapp.services.EmailService;
import org.musicrecital.webapp.util.RequestUtil;
import org.slf4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Managed Bean to send password hints to registered users.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @author Serge Eby
 * @version $Id$
 */
public class PasswordHint {
    @Inject
    private Logger logger;

    @Inject
    private Messages messages;

    @Inject
    private UserManager userManager;

    @Inject
    private EmailService emailService;

    @Inject
    private AlertManager alertManager;

    @Inject
    private HttpServletRequest request;

    private String username;

    Object onActivate(EventContext ctx) {
        // ensure that the username has been set
        if (ctx == null || ctx.getCount() == 0) {
            logger.warn("Username not specified, notifying user that it's a required field.");
            alertManager.alert(Duration.TRANSIENT,
                    Severity.ERROR,
                    messages.format("errors.required", messages.get("user.username")));

            return Login.class;
        }

        // Expect username is the first item in the context
        int userIdx = 0;
        this.username = ctx.get(String.class, userIdx).trim();
        logger.debug("Processing Password Hint for username: " + username);

        // look up the user's information
        try {
            User user = userManager.getUserByUsername(username);

            StringBuilder msg = new StringBuilder();
            msg.append("Your password hint is: ").append(user.getPasswordHint());
            String subject = '[' + messages.get("webapp.name") + "] " + messages.get("user.passwordHint");

            emailService.send(user, subject, msg.toString(), RequestUtil.getAppURL(request), true);

            alertManager.alert(Duration.TRANSIENT,
                    Severity.INFO,
                    messages.format("login.passwordHint.sent", username, user.getEmail()));
        } catch (UsernameNotFoundException e) {
            logger.warn(e.getMessage());
            // If exception is expected do not rethrow
            alertManager.error(messages.format("login.passwordHint.error", username));

        } catch (MailException me) {
            alertManager.error(me.getCause().getLocalizedMessage());
        }


        return Login.class;
    }


}
