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

package org.musicrecital.webapp.data;

import org.apache.tapestry5.upload.services.UploadedFile;

import java.io.File;
import java.io.Serializable;

/**
 * Wrapper class around the attributes used by the upload process
 *
 * @author Serge Eby
 * @author modifications <a href="mailto:dlwhitehurst@gmail.com">David L. Whitehurst</a>
 * @version $Id$
 * 
 */
public class FileData implements Serializable {

    /**
     * 
     */
	private UploadedFile file;
    
	/**
	 * 
	 */
	private String friendlyName;
    
	/**
	 * 
	 */
	private String path;
    
	/**
	 * 
	 */
	private String url;

    /**
     * 
     * @return
     */
	public UploadedFile getFile() {
        return file;
    }

	/**
	 * 
	 * @param file
	 */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * 
     * @return
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    /**
     * 
     * @param friendlyName
     */
    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    /**
     * 
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets URL
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     */
    public String getFileName() {
        return file != null ? file.getFileName() : null;
    }

    /**
     * 
     * @return
     */
    public Long getSize() {
        return file != null ? Long.valueOf(file.getSize()) : null;
    }

    /**
     * 
     * @return
     */
    public String getContentType() {
        return file != null ? file.getContentType() : null;
    }

    /**
     * 
     * @param another
     */
    public void write(File another) {
        file.write(another);
    }
}
