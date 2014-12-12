/**
 * Copyright 2014 MusicRecital Project
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
 * Code developed and licensed here for org.musicrecital packaging was 
 * formulated, copied, written, drafted, or otherwise created by 
 * the MusicRecital project at http://musicrecital.org. This license is 
 * freely available to the general public and is used here because 
 * it provides the foundation for the use, duplication, and sharing 
 * of open source software code. The cummulative code base under this 
 * packaging was not written, maintained, or otherwise deployed by 
 * the Apache Group. This code does not reflect the technological 
 * growth at apache.org or the Apache Software Foundation community 
 * collective.
 *
 */


package org.musicrecital;

/**
 * Constant values used throughout the application.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @author modifications <a href="mailto:dlwhitehurst@gmail.com">David L. Whitehurst</a>
 */
public final class Constants {

    private Constants() {
        // hide me
    }
    //~ Static fields/initializers =============================================

    /**
     * Assets Version constant
     */
    public static final String ASSETS_VERSION = "assetsVersion";
    /**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /**
     * User home from System properties
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role
     */
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    /**
     * The name of the User role
     */
    public static final String USER_ROLE = "ROLE_USER";

    /**
     * The name of the Listener role
     */
    public static final String LISTENER_ROLE = "ROLE_LISTENER";

    /**
     * The name of the Performer role
     */
    public static final String PERFORMER_ROLE = "ROLE_PERFORMER";

    /**
     * The name of the MusicRecitalCoordinator role
     */
    public static final String COORDINATOR_ROLE = "ROLE_COORDINATOR";

    /**
     * The name of the Accompanist role
     */
    public static final String ACCOMPANIST_ROLE = "ROLE_ACCOMPANIST";
    
    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     * @deprecated No longer used to set themes.
     */
    public static final String CSS_THEME = "csstheme";
}
