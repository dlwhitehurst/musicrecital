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
import org.apache.tapestry5.Link;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.musicrecital.service.UserManager;
import org.musicrecital.webapp.services.EmailService;
import org.musicrecital.webapp.util.RequestUtil;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Generates a password recovery token and sends it via email to user.
 *
 */
public class PasswordRecoveryToken {

    @Inject
    private Logger logger;

    @Inject
    private Messages messages;

    @Inject
    private UserManager userManager;


    @Inject
    private AlertManager alertManager;

    @Inject
    private HttpServletRequest request;


    @Inject
    private PageRenderLinkSource pageRenderLinkSource;


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

        logger.debug("Sending recovery token for username: " + username);

        try {
            userManager.sendPasswordRecoveryEmail(username, RequestUtil.getAppURL(request) + getLink());
        } catch (final UsernameNotFoundException ignored) {
            // lets ignore this
            Throwable exceptionToLog = ignored.getCause() != null ? ignored.getCause() : ignored;
            logger.error(exceptionToLog.getLocalizedMessage());
        }

        alertManager.alert(Duration.TRANSIENT,
                Severity.INFO,
                messages.get("updatePassword.recoveryToken.sent"));


        return Login.class;
    }

    /**
     * Build link for for password reset
     * @return URI
     */
    public String getLink() {
        Link link = pageRenderLinkSource.createPageRenderLinkWithContext(PasswordUpdate.class);
        link.addParameter("username", "{username}");
        link.addParameter("token", "{token}");
        return link.toURI();
    }

}
