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

import org.musicrecital.Constants;
import org.musicrecital.model.Role;
import org.musicrecital.model.User;
import org.musicrecital.webapp.services.SecurityContext;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Security context implementation based on Spring Security
 *
 * @author Serge Eby
 */
public class SpringSecurityContext implements SecurityContext {

    private final static Pattern COMMA_PATTERN = Pattern.compile("\\s*,\\s*");

    public boolean isLoggedIn() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            if ("anonymousUser".equals(authentication.getName())) {
                return false;
            }
            return authentication.isAuthenticated();
        }
        return false;
    }

    public UserDetails getUserDetails() {

        UserDetails userDetails = null;
        if (isLoggedIn()) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                userDetails = (UserDetails) principal;
            }
        }
        return userDetails;
    }


    public User getUser() {
        User user = null;
        if (isLoggedIn()) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                user = (User) principal;
            }
        }
        return user;
    }

    public String getUsername() {
        User user = getUser();
        return user != null ? user.getUsername() : null;
    }

    public boolean hasRoles(String roleName) {
        // If no role defined, return true
        if (roleName == null) {
            return true;
        }

        User user = getUser();
        //TODO: User InternalUtils class??
        List<String> allowedRoles = Arrays.asList(COMMA_PATTERN.split(roleName.trim()));
        if (user != null) {
            for (Role role : user.getRoles()) {
                if (allowedRoles.contains(role.getName())) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isAdmin() {
        return hasRoles(Constants.ADMIN_ROLE);
    }

    public void logout() {
        // NYI
    }

    public boolean isRememberMe() {
        AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return resolver.isRememberMe(authentication);
    }

    public void login(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        UsernamePasswordAuthenticationToken loggedIn = new UsernamePasswordAuthenticationToken(
                user,
                user.getConfirmPassword(),
                user.getAuthorities());

        loggedIn.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(loggedIn);

    }

}
