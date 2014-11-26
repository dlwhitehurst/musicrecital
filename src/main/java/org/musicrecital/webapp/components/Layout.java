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

package org.musicrecital.webapp.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.SymbolSource;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.musicrecital.webapp.AppFuseSymbolConstants;
import org.musicrecital.webapp.services.SecurityContext;


/**
 * Global Layout component
 *
 * @author Serge Eby
 * @version $Id$
 */

@Import(stack = AppFuseSymbolConstants.BOOTSTRAP_STACK)
public class Layout {

    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.MESSAGE)
    private String title;


    @Property
    @Parameter(defaultPrefix = BindingConstants.MESSAGE)
    private String heading;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String menu;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String bodyId;


    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String bodyClass;


    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private Block sidebar;

    @Inject
    private Environment environment;

    @Inject
    private Context context;

    @Inject
    private Messages messages;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ComponentResources resources;

    @Inject
    private PageRenderLinkSource linkSource;

    @Inject
    private JavaScriptSupport jsSupport;

    @Inject
    private AssetSource assetSource;

    @Inject
    private SymbolSource symbolSource;

    @Inject
    private Block adminMenuBlock;


    public boolean isLoggedIn() {
        return securityContext.isLoggedIn();
    }

    public Block getSidebar() {
        if ("AdminMenu".equals(menu) && securityContext.isAdmin()) {
            return adminMenuBlock;
        }
        return null;
    }


    public boolean isAdminMenu() {
        return "AdminMenu".equals(menu);
    }


    public String getCurrentPage() {
        return resources.getPageName().toLowerCase();
    }

//    public String getSidebarClass() {
//        resources.getPageName().equalsIgnoreCase(pageName);
//    }

    void afterRender() {
        jsSupport.addScript("$j('.dropdown-menu li').click(function(){ $j(this).addClass('active');});");
    }

}
