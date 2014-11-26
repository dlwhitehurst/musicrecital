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

package org.musicrecital.webapp.pages.admin;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.musicrecital.dao.SearchException;
import org.musicrecital.model.User;
import org.musicrecital.service.UserManager;
import org.musicrecital.webapp.pages.Home;
import org.musicrecital.webapp.pages.UserEdit;
import org.slf4j.Logger;

import java.util.List;

/**
 * @author Serge Eby
 * @version $Id$
 */
public class UserList {
    @Inject
    private Logger logger;

    @Inject
    private Messages messages;

    @Inject
    private UserManager userManager;

    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private JavaScriptSupport jsSupport;

    @InjectPage
    private UserEdit userEdit;

    @Property
    private User currentUser;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String q;

    private String infoMessage;

    @Property
    private String errorMessage;

    @Property
    private List<User> users;

    public BeanModel<User> getModel() {
        final BeanModel<User> model = beanModelSource.createDisplayModel(User.class, messages);

        model.include("username", "email", "enabled");
        model.add("fullname");
        // Set labels
        model.get("username").label(messages.get("user.username"));
        model.get("email").label(messages.get("user.email"));
        model.get("enabled").label(messages.get("user.enabled"));
        model.get("fullname").label(messages.get("activeUsers.fullName"));

        return model;
    }

    void setupRender() {
        try {
            users = userManager.search(q);
        } catch (SearchException se) {
            errorMessage = se.getMessage();
            users = userManager.getUsers();
        }
    }

    Object onAdd() {
        return userEdit.initialize(null, "list", messages.get("userProfile.admin.message"));
    }

    Object onDone() {
        return Home.class;
    }

    Object onActionFromEdit(User user) {
        logger.debug("fetching user with id: " + user.getId());
        user.setConfirmPassword(user.getPassword());
        return userEdit.initialize(user, "list", messages.get("userProfile.admin.message"));
    }

    Object onSubmit() {
        return this;
    }
}
