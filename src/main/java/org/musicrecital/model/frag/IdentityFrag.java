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


import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:dlwhitehurst@me.com">David L. Whitehurst</a>
 * @version $Id$
 */
@Embeddable
@Indexed
public class IdentityFrag implements Serializable {

    private static final long serialVersionUID = -2923986047414959304L;

    private String createdBy;

    private String modifiedBy;

    private Date created;

    private Date modified;

   	private String key;

    private String name;

    private String heading;

    private String description;

// ********** GETTERS ****************************************************************

    @Column(name="[created_by]", length = 40)
    @Field
    public String getCreatedBy() {
        return createdBy;
    }

    @Column(name="[create_date]")
    @Field(analyze= Analyze.NO)
    public Date getCreated() {
        return created;
    }

    @Column(name="[modified_by]", length = 40)
    @Field
    public String getModifiedBy() {
        return modifiedBy;
    }

    @Column(name="[modified_date]")
    @Field(analyze= Analyze.NO)
    public Date getModified() {
        return modified;
    }

    @Column(name="[key]", length = 5)
    @Field
   	public String getKey() {
   		return key;
   	}

    @Column(name="[name]", length = 40)
    @Field
    public String getName() {
        return name;
    }

    @Column(name="[heading]", length = 80)
    @Field
    public String getHeading() {
        return heading;
    }

    @Column(name="[description]", length = 2048)
    @Field
    public String getDescription() {
        return description;
    }

// ********** SETTERS ****************************************************************
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setModifiedBy(String modifiedBy) {
         this.modifiedBy = modifiedBy;
     }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public void setKey(String key) {
   		this.key = key;
   	}

    public void setName(String name) {
        this.name = name;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
