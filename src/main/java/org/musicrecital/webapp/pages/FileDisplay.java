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

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.musicrecital.webapp.data.FileData;

/**
 * This class handles the uploading of a file and writing it to
 * the filesystem.  Eventually, it will also addChild support for persisting the
 * files information into the database.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @author Serge Eby
 * @version $Id$
 */
public class FileDisplay {


    @Persist(PersistenceConstants.FLASH)
    @Property(write = false)
    private FileData fileData;


    public void setFileData(FileData fileData) {
        this.fileData = fileData;
    }

    Object onDone() {
        return Home.class;
    }

    Object onAnotherUpload() {
        return FileUpload.class;
    }

}
