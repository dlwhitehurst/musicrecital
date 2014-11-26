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

package org.musicrecital.webapp.services.javascript;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.ioc.services.SymbolSource;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;

import java.util.Collections;
import java.util.List;

/**
 * Add Twitter Bootstrap Stack to Tapestry
 *
 * @author Serge Eby
 */
public class BootstrapJavaScriptStack implements JavaScriptStack {

    private final List<Asset> jsStack;
    private final List<StylesheetLink> cssStack;
    private SymbolSource symbolSource;

    public BootstrapJavaScriptStack(final AssetSource assetSource, final SymbolSource symbolSource) {
        this.jsStack = CollectionFactory.newList();
        jsStack.add(0, assetSource.getContextAsset("scripts/jquery.noconflict.js", null));
        jsStack.add(0, assetSource.getClasspathAsset("classpath:/META-INF/resources/webjars/jquery/1.8.3/jquery.min.js"));
        jsStack.add(assetSource.getClasspathAsset("classpath:/META-INF/resources/webjars/bootstrap/3.0.2/js/bootstrap.min.js"));
        jsStack.add(assetSource.getContextAsset("scripts/lib/plugins/jquery.cookie.js", null));
        jsStack.add(assetSource.getContextAsset("scripts/script.js", null));

        this.cssStack = CollectionFactory.newList();
        cssStack.add(new StylesheetLink(assetSource.getClasspathAsset("classpath:/META-INF/resources/webjars/bootswatch/3.0.0/spacelab/bootstrap.min.css")));
        cssStack.add(new StylesheetLink(assetSource.getContextAsset("styles/style.css", null)));
        cssStack.add(new StylesheetLink(assetSource.getContextAsset("styles/t5-override.css", null)));
    }

    public List<String> getStacks() {
        return Collections.emptyList();
    }

    public List<Asset> getJavaScriptLibraries() {
        return jsStack;
    }

    public List<StylesheetLink> getStylesheets() {
        return cssStack;
    }

    public String getInitialization() {
        return null;
    }
}
