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

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.musicrecital.model.User;
import org.musicrecital.webapp.pages.Home;

import java.util.Set;

/**
 * Lists all active users
 *
 * @author Serge Eby
 * @version $Id$
 */
public class ActiveUsers {

    @Inject
    private Messages messages;

    @Property
    private User currentUser;

    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Inject
    private Context context;


    public BeanModel<User> getModel() {
        final BeanModel<User> model = beanModelSource.createDisplayModel(User.class, messages);
        model.include("username");
        model.add("fullname");
        // Set labels
        model.get("username").label(messages.get("user.username"));
        model.get("fullname").label(messages.get("activeUsers.fullName"));

        return model;
    }

    @SuppressWarnings("unchecked")
    public Set<User> getActiveUsers() {
        return (Set<User>) context.getAttribute("userNames");
    }

    public String getHomeLink() {
        return pageRenderLinkSource.createPageRenderLink(Home.class).toURI();
    }
}
