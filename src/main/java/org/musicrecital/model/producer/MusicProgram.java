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
 * Code developed and licensed here for com.dlw packaging was 
 * formulated, copied, written, drafted, or otherwise created by 
 * David L. Whitehurst, at dlwhitehurst@me.com. This license is 
 * freely available to the general public and is used here because 
 * it provides the foundation for the use, duplication, and sharing 
 * of open source software code. The cummulative code base under this 
 * packaging was not written, maintained, or otherwise deployed by 
 * the Apache Group. This code does not reflect the technological 
 * growth at apache.org or the Apache Software Foundation community 
 * collective.
 *
 */

package org.musicrecital.model.producer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.musicrecital.model.BaseObject;
import org.musicrecital.model.repertoire.Piece;

/**
 * This class represents the music program. The collection of pieces put together
 * by a producer. The music program represents the music to be played at a music
 * event.
 * 
 * @author <a href="mailto:dlwhitehurst@me.com">David L. Whitehurst</a>
 * @version $Id$
 */
@Entity
@Table(name = "music_program")
public class MusicProgram extends BaseObject {
	
	/**
	 * Relational database key or identification number
	 */
	private Long id;
	
	/**
	 * Name or describing statement defining this music program
	 */
	protected String programName;
	
	/**
	 * The MusicRecital user that created this music program, and was identified and
	 * deemed an actual Producer based on the user's merit, or just reward. 
	 */
	protected Producer producer;
	
	/**
	 * Collection of pieces, songs, solos, orchestrations, etc.
	 */
	protected Set<Piece> pieces = new HashSet<Piece>(); // empty but not-null set
	
	/**
	 * Initial creation date
	 */
	protected Date created;
	
	/**
	 * Date modified after initial creation, that date will remain unchanged.
	 */
	protected Date modified;
	
	/**
	 * Id (key) identifying the User that created this object or database record 
	 */
	protected Long createdBy;
	
	/**
	 * Id (key) identifying the User that has modified this object or database record
	 */
	protected Long modifiedBy;
	

	/* (non-Javadoc)
	 * @see org.musicrecital.model.BaseObject#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.BaseObject#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the id
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the programName
	 */
    @Column(nullable = false, length = 80, unique = true)
    @Field
	public String getProgramName() {
		return programName;
	}

	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}

	/**
	 * @return the producer
	 */
    @Embedded
    @IndexedEmbedded
	public Producer getProducer() {
		return producer;
	}

	/**
	 * @param producer the producer to set
	 */
	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	/**
	 * @return the pieces
	 */
/*    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "program_piece",
            joinColumns = { @JoinColumn(name = "program_id") },
            inverseJoinColumns = @JoinColumn(name = "piece_id")
    )
*/	
	@Transient
	public Set<Piece> getPieces() {
		return pieces;
	}

	/**
	 * @param pieces the pieces to set
	 */
	public void setPieces(Set<Piece> pieces) {
		this.pieces = pieces;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	public Long getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
