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

package org.musicrecital.webapp.data;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.musicrecital.model.User;

import java.io.Serializable;

/**
 * User Session Class to hold the current User
 *
 * @author <a href="mailto:dlwhitehurst@gmail.com">David L. Whitehurst</a>
 * @version $Id$
 * 
 */
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1932462707656603990L;
    private User currentUser;
    private boolean cookieLogin;

    /**
     * @return Returns the cookieLogin.
     */
    public boolean isCookieLogin() {
        return cookieLogin;
    }

    /**
     * @param cookieLogin The cookieLogin to set.
     */
    public void setCookieLogin(boolean cookieLogin) {
        this.cookieLogin = cookieLogin;
    }

    /**
     * @return Returns the currentUser.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser The currentUser to set.
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
