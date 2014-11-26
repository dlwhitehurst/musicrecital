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

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * Logout Page
 * 
 * @author Serge Eby
 * @version $Id$
 *
 */
public class Logout {

    @Inject
    private Request request;

    @Inject
    private Cookies cookies;

    Object onActivate() {

        // Clear session
        Session session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Remove RememberMe cookie
        cookies.removeCookieValue(TokenBasedRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);

        return Home.class;
    }
}
