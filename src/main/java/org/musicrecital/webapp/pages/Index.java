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
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Response;
import org.slf4j.Logger;

import java.util.List;

/**
 * Main index page. This also handles 404 errors
 *
 * @author Serge Eby
 * @version $Id$
 */
public class Index {

    @Inject
    private Logger logger;

    @Inject
    private RequestGlobals globals;

    @Inject
    private Request request;

    @Inject
    private Response response;


    @SuppressWarnings("unchecked")
    private List eventContext;

    @SuppressWarnings("unchecked")
    public List getEventContext() {
        return eventContext;
    }

    Object onActivate(List context) {
        eventContext = context;
        if (context != null && !context.isEmpty()) {
            int index = 0;
            for (Object obj : context) {
                index++;
                logger.debug(String.format("Context #%d =  %s", index, obj.toString()));
            }
            logger.debug("Redirecting to PageNotFound");
            return NotFound.class;
        }
        // Redirect to Home
        return Home.class;

    }
}
