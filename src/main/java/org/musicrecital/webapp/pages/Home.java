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

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.musicrecital.model.User;
import org.musicrecital.webapp.services.SecurityContext;
import org.slf4j.Logger;


/**
 * Main entry point of the application
 *
 * @author Serge Eby
 * @version $Id$
 */
public class Home {

    @Inject
    private Logger logger;

    @Inject
    private Messages messages;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private AlertManager alertManager;

    @InjectPage
    private UserEdit userEdit;

    @Persist
    @Property
    private User currentUser;

    Object onActionFromEditProfile() {
        logger.debug("Editing current user's profile");
        currentUser = securityContext.getUser();
        if (currentUser == null) {
            logger.debug("Current User is null - this is unexpected");
            return this;
        }
        logger.debug(String.format("Current User is %s", currentUser.getUsername()));
        currentUser.setConfirmPassword(currentUser.getPassword());
        return userEdit.initialize(currentUser, "main", messages.get("userProfile.message"));
    }
}
