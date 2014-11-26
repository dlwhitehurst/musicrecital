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

package org.musicrecital.webapp.services.impl;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.musicrecital.model.User;
import org.musicrecital.service.UserManager;


public class UserEncoder implements ValueEncoder<User> {

    private UserManager userManager;

    public UserEncoder(UserManager userManager) {
        this.userManager = userManager;
    }

    public String toClient(User value) {
        return value != null ? String.valueOf(value.getId()) : null;
    }

    public User toValue(String clientValue) {

        if (clientValue == null || "null".equals(clientValue)) {
            return null;
        }

        Long id = null;
        try {
            id = Long.valueOf(clientValue);
        }
        catch (Exception ex) {
            return new User();
        }
        User user = userManager.get(id);
        // Reset confirmed password
        if (user != null) {
            user.setConfirmPassword(user.getPassword());
        }

        return user;
    }

}
