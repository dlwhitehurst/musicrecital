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
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.musicrecital.model.User;
import org.musicrecital.service.UserExistsException;
import org.musicrecital.service.UserManager;
import org.musicrecital.webapp.util.RequestUtil;
import org.slf4j.Logger;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

/**
 * Updates a registered user's password.
 *
 * @author Serge Eby
 */
public class PasswordUpdate {
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

    @ActivationRequestParameter(value = "username")
    @Property
    private String username;

    @ActivationRequestParameter(value = "token")
    @Property
    private String token;

    @Property
    private String currentPassword;


    @Property
    @Persist(PersistenceConstants.FLASH)
    private String newPassword;


    void setupRender() {
        if (StringUtils.isBlank(username)) {
            username = request.getRemoteUser();
        }
    }

    @Log
    void onValidateFromPasswordUpdate() {

        // Validate token
        if (StringUtils.isNotBlank(token) && !userManager.isRecoveryTokenValid(username, token)) {
            alertManager.error(messages.get("updatePassword.invalidToken"));
        }

        // Validate user access (if logged in)
        String remoteUser = request.getRemoteUser();
        if (remoteUser != null && !username.equals(remoteUser)) {
            throw new AccessDeniedException("You do not have permission to modify other users password.");
        }


        // Ensure new password is not empty
        if (StringUtils.isEmpty(newPassword)) {
            alertManager.error(messages.format("errors.required", messages.get("updatePassword.newPassword.label")));
        }

    }

    Object onSuccess() throws UserExistsException {

        final User user = userManager.updatePassword(username, currentPassword, token, newPassword,
                RequestUtil.getAppURL(request));
        if (user != null) {
            alertManager.alert(Duration.TRANSIENT,
                    Severity.INFO,
                    messages.format("updatePassword.success", username));

        } else {
            String errorMessageKey = StringUtils.isNotBlank(token) ?
                    "updatePassword.invalidToken" : "updatePassword.invalidPassword";

            alertManager.error(messages.get(errorMessageKey));
            return this;
        }

        return Home.class;

    }

    Object onCancel() {
        return Home.class;
    }

    Object onFailure() {
        return this;
    }


}
