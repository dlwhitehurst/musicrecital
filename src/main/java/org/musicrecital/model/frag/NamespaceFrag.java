/**
 * Copyright 2014 MusicRecital Project
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
 * Code developed and licensed here for org.musicrecital packaging was 
 * formulated, copied, written, drafted, or otherwise created by 
 * the MusicRecital project at http://musicrecital.org. This license is 
 * freely available to the general public and is used here because 
 * it provides the foundation for the use, duplication, and sharing 
 * of open source software code. The cummulative code base under this 
 * packaging was not written, maintained, or otherwise deployed by 
 * the Apache Group. This code does not reflect the technological 
 * growth at apache.org or the Apache Software Foundation community 
 * collective.
 *
 */

package org.musicrecital.model.frag;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * This class is used to name an object using a short key e.g. MUS, a namespace e.g.
 * MusicRecital, a more formal label e.g. "The MusicRecital Project", and a
 * description or larger String space e.g. "The MusicRecital project was created
 * to create and provide a local network of talented musicians, support people, and 
 * music enthusiasts" The structure of this class models a small piece or fragment
 * of a larger composite object. And, the embeddable annotation allows this object
 * to replicate the fragment anywhere these columns would be of use in the composite
 * object's table. The Address class is easily understood and may be moved to 
 * this package and renamed in the future.
 * 
 * Notes: As e.g. MusicRecital Agile Samurai was the name for a Confluence space,
 * Atlassian's java script created key "MAS" in the web form prior to saving.
 * 
 * @author <a href="mailto:dlwhitehurst@me.com">David L. Whitehurst</a>
 * @version $Id$
 */
@Embeddable
@Indexed
public class NamespaceFrag {
	
	/**
	 * An acronym-type string key limited to 5 characters
	 */
	private String key; // e.g. MUS
	
	/**
	 * The generic name for the persisted object
	 */
	private String name; // e.g. MusicRecital
	
	/**
	 * A formatted name used like a heading or more descriptive text
	 */
	private String heading; // e.g. The Music Recital Project (format considered)
	
	/**
	 * A place for a lengthy description of the persisted object
	 */
	private String description; // e.g. Para. text ... 2048 characters

	/**
	 * @return the key
	 */
    @Column(length = 5)
    @Field
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the name
	 */
    @Column(length = 40)
    @Field
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the heading
	 */
    @Column(length = 80)
    @Field
	public String getHeading() {
		return heading;
	}

	/**
	 * @param heading the heading to set
	 */
	public void setHeading(String heading) {
		this.heading = heading;
	}

	/**
	 * @return the description
	 */
    @Column(length = 2048)
    @Field
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
