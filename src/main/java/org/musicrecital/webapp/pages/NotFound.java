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
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

/**
 * Page to be displayed whenever a page is not found (404 error)
 *
 * @author Serge Eby
 * @version $Id$
 */
public class NotFound {

    @Property
    @Inject
    @Path("context:images/404.jpg")
    private Asset notFoundImage;

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Inject
    private Messages messages;

    public String getNotFoundMessage() {
        String url = pageRenderLinkSource.createPageRenderLink(Home.class).toURI();
        return messages.format("404.message", url);
    }


}
