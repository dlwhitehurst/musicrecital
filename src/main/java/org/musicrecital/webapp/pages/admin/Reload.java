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

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationGlobals;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.musicrecital.webapp.listener.StartupListener;
import org.musicrecital.webapp.pages.Home;

import java.io.IOException;

/**
 * @author Serge Eby
 * @version $Id$
 */
public class Reload {
    @Inject
    private Messages messages;

    @Inject
    private ApplicationGlobals globals;

    @Inject
    private AlertManager alertManager;


    Object onActivate() throws IOException {
        StartupListener.setupContext(globals.getServletContext());
        alertManager.alert(Duration.TRANSIENT,
                Severity.INFO,
                messages.get("reload.succeeded"));
        return Home.class;
    }

}
