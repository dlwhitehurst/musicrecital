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

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.musicrecital.Constants;
import org.musicrecital.webapp.AppFuseSymbolConstants;
import org.slf4j.Logger;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Login Page
 *
 * @author Serge Eby
 * @version $Id$
 */


@Import(library = {"context:scripts/login.js"})
public class Login {

    @Inject
    private Logger logger;

    @Inject
    @Symbol(AppFuseSymbolConstants.SECURITY_URL)
    private String securityUrl;

    @Inject
    private Request request;

    @Inject
    private Messages messages;

    @Inject
    private AlertManager alertManager;

    @Inject
    private PageRenderLinkSource pageRendererLinkSource;


    @Environmental
    private JavaScriptSupport javascriptSupport;

    @Property
    private String errorMessage;


    @Inject
    private Context context;


    @Log
    void onActivate(String loginError) {
        if ("error".equals(loginError)) {
            this.errorMessage = ((Exception) request
                    .getSession(true)
                    .getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY))
                    .getMessage();
            logger.error(String.format("Error while attempting to login: %s",
                    errorMessage));
        }

    }

    String onPassivate() {
        return errorMessage;
    }

    void afterRender() {
        JSONObject spec = new JSONObject();

        String requiredUsernameError = messages.format("errors.required",
                messages.get("label.username"));
        String requiredPasswordError = messages.format("errors.required",
                messages.get("label.password"));

        spec.put("url", createLink(this.getClass()))
                .put("passwordHintLink", createLink(PasswordHint.class))
                .put("requiredUsername", requiredUsernameError)
                .put("requiredPassword", requiredPasswordError);

        // javascriptSupport.addScript("initialize(%s);", spec);
      //  javascriptSupport.addInitializerCall("loginHint", spec);

    }


    public String getSpringSecurityUrl() {
        return request.getContextPath() + securityUrl;
    }

    void cleanupRender() {
        this.errorMessage = null;
    }

    @SuppressWarnings("unchecked")
    public boolean isRememberMeEnabled() {
        try {
            Map config = (HashMap) context.getAttribute(Constants.CONFIG);
            if (config != null) {
                return (config.get("rememberMeEnabled") != null);
            }
        } catch (UnsupportedOperationException uoe) {
            // only happens in tests
            // getAttribute() is not supported for ContextForPageTester
            logger.warn(uoe.getMessage());
        }
        return false;
    }

    public String getSignupLink() {
        String link = createLink(Signup.class);
        return messages.format("login.signup", link);
    }


    private String createLink(Class clazz) {
        return pageRendererLinkSource.createPageRenderLink(clazz).toAbsoluteURI();
    }

}
