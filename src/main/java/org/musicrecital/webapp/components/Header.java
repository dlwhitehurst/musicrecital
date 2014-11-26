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

package org.musicrecital.webapp.components;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PersistentLocale;

import java.util.Locale;

/**
 * AppFuse Header component
 *
 * @author Serge Eby
 */
public class Header {

    @Inject
    private Locale locale;

    @Inject
    private PersistentLocale persistentLocale;

    public boolean isEnglish() {
        return locale.getLanguage().
                equals(new Locale("en", "", "").getLanguage());
    }

    Object onActionFromSwitch() {
        persistentLocale.set(Locale.ENGLISH);
        return this;
    }
}
