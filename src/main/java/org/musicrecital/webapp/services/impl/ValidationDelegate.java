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

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationDecorator;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.services.Environment;

/**
 * Custom Implementation of the ValidationDecorator Interface
 *
 * @author Serge Eby
 * @version $Id$
 */
public class ValidationDelegate implements ValidationDecorator {

    private final Environment environment;
    private final Asset iconAsset;
    private final MarkupWriter markupWriter;

    public ValidationDelegate(Environment environment, Asset iconAsset,
                              MarkupWriter markupWriter) {
        this.environment = environment;
        this.markupWriter = markupWriter;
        this.iconAsset = iconAsset;
    }

    public void insideField(Field field) {

        if (inError(field)) {
            if (field.isRequired() && isMissing(field)) {
                addErrorClassToCurrentElement("fieldMissing");
                return;
            }
            addErrorClassToCurrentElement("fieldInvalid");
        }
    }

    public void beforeLabel(Field field) {

    }

    public void insideLabel(Field field, Element labelElement) {
        if (inError(field)) {
            addErrorClassToCurrentElement("error");
        }
        /* http://www.nabble.com/Problem-using-BeanEditForm-with-a-POJO-td23349016s302.html#a23352398
        if (field.isRequired()) {
            labelElement.raw("<span class=\"req\"> *</span>");
        }*/

    }

    public void afterLabel(Field field) {
    }

    public void beforeField(Field field) {
        if (inError(field)) {
            markupWriter.element("span", "class", "fieldError");
            String iconId = field.getClientId() + ":icon";
            markupWriter.element("img", "src", iconAsset.toClientURL(), "alt",
                    "", "class", "validationWarning", "id", iconId);
            markupWriter.end(); // img
            markupWriter.writeRaw("&nbsp;");

            String error = getError(field);
            if (error == null) {
                error = "";
            }
            markupWriter.writeRaw(error);
            markupWriter.end(); // span
        }

    }

    public void afterField(Field field) {
    }

    private boolean inError(Field field) {
        ValidationTracker tracker = environment
                .peekRequired(ValidationTracker.class);

        return tracker.inError(field);
    }

    private String getError(Field field) {
        ValidationTracker tracker = environment
                .peekRequired(ValidationTracker.class);
        return tracker.getError(field);
    }

    private boolean isMissing(Field field) {
        return true; // FIXME: Determine if field wasn't populated
    }

    private void addErrorClassToCurrentElement(String errorClass) {
        markupWriter.getElement().addClassName(errorClass);
    }

}
