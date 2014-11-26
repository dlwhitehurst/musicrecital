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
import org.musicrecital.model.Role;
import org.musicrecital.service.RoleManager;


public class RoleEncoder implements ValueEncoder<Role> {

    private RoleManager roleManager;

    public RoleEncoder(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    public String toClient(Role value) {
        return String.valueOf(value.getId());
    }

    public Role toValue(String clientValue) {
        // happens when testing with iBatis for some reason
        if ("null".equals(clientValue)) {
            return null;
        }

        Long id = Long.valueOf(clientValue);
        return roleManager.get(id);
    }

}
