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

package org.musicrecital.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;

import org.hibernate.search.annotations.IndexedEmbedded;
import org.musicrecital.model.frag.AuditingFrag;
import org.musicrecital.model.frag.NamespaceFrag;

/**
 * @author <a href="mailto:dlwhitehurst@gmail.com">David L. Whitehurst</a>
 * @version $Id$
 */
public class Qualification extends BaseObject {

	/**
	 * Unique serial identifier, serializable object
	 */
	private static final long serialVersionUID = 5145022929918447856L;

	/**
	 * Primary key
	 */
	private Long id;
	
	/**
	 * Set of parent Roles to which this Qualification is assigned
	 */
	private Set<Role> parentRoles = new HashSet<Role>();
	
	/**
	 * Date that this qualification was defined for the role
	 */
	private Date qualificationToRoleSpecified;
	
	/**
	 * CreatedBy, Created, ModifiedBy, Modified embedded fragment
	 */
	private AuditingFrag auditing = new AuditingFrag();
	
	/**
	 * Namespace embedded fragment
	 */
	private NamespaceFrag namespace = new NamespaceFrag();
	
	/**
	 * Boolean to denote whether this Qualification has been reviewed
	 */
	private Boolean isReviewed;
	
	/**
	 * Date to schedule next review
	 */
	private Date reviewNeeded;
	
	/**
	 * Date to schedule next approval
	 */
	private Date approvalNeeded;
	
	/**
	 * Boolean to denote whether this Qualification has been reviewed
	 */
	private Boolean isApproved;

    /**
     * Default constructor - creates a new instance with no values set.
     */
	public Qualification() {}
	
	/**
	 * Constructor with values needed set and with defaults
	 * @param name
	 * @param key
	 * @param description
	 */
	public Qualification(String name, String key, String description) {
		namespace.setName(name);
		namespace.setKey(key);
		namespace.setDescription(description);
		isApproved = false;
		isReviewed = false;
		qualificationToRoleSpecified = new Date();
	}
	
	/* (non-Javadoc)
	 * @see org.musicrecital.model.BaseObject#toString()
	 */
	@Override
	public String toString() {
		return getNamespace().getDescription(); // probably for tooltip text
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		
		boolean retVal = false;
        
		// truly the exact same instance of the object
		if (this == o) {
            retVal =  true;
        }
		// not Qualification type, can't equal
        if ((o instanceof Qualification)) {
            retVal = true;
        }
        // a Qualification object and could be the same Qualification literal not virtual 
        final Qualification qualification = (Qualification) o;

        if (this.getNamespace().getDescription().equals(qualification.getNamespace().getDescription())) {
        	retVal = true;
        }
        return retVal;
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.BaseObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return (getNamespace().getDescription() != null ? getNamespace().getDescription().hashCode() : 0);		
	}

	/**
	 * @return the id
	 */
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
	 * @return the qualificationToRoleSpecified
	 */
	public Date getQualificationToRoleSpecified() {
		return qualificationToRoleSpecified;
	}

	/**
	 * @param qualificationToRoleSpecified the qualificationToRoleSpecified to set
	 */
	public void setQualificationToRoleSpecified(Date qualificationToRoleSpecified) {
		this.qualificationToRoleSpecified = qualificationToRoleSpecified;
	}

	/**
	 * @return the auditing fragment
	 */
	@Embedded
    @IndexedEmbedded
	public AuditingFrag getAuditing() {
		return auditing;
	}

	/**
	 * @param auditing the auditing to set
	 */
	public void setAuditing(AuditingFrag auditing) {
		this.auditing = auditing;
	}

	/**
	 * @return the namespace fragment
	 */
	@Embedded
    @IndexedEmbedded
	public NamespaceFrag getNamespace() {
		return namespace;
	}

	/**
	 * @param namespace the namespace to set
	 */
	public void setNamespace(NamespaceFrag namespace) {
		this.namespace = namespace;
	}

	/**
	 * @return the isReviewed Boolean
	 */
	public Boolean getIsReviewed() {
		return isReviewed;
	}

	/**
	 * @param isReviewed the isReviewed to set
	 */
	public void setIsReviewed(Boolean isReviewed) {
		this.isReviewed = isReviewed;
	}

	/**
	 * @return the isApproved Boolean
	 */
	public Boolean getIsApproved() {
		return isApproved;
	}

	/**
	 * @param isApproved the isApproved to set
	 */
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	/**
	 * @return the parentRoles set
	 */
	public Set<Role> getParentRoles() {
		return parentRoles;
	}

	/**
	 * @param parentRoles the parentRoles to set
	 */
	public void setParentRoles(Set<Role> parentRoles) {
		this.parentRoles = parentRoles;
	}

	/**
	 * @return the reviewNeeded scheduled date
	 */
	public Date getReviewNeeded() {
		return reviewNeeded;
	}

	/**
	 * @param reviewNeeded the reviewNeeded to set
	 */
	public void setReviewNeeded(Date reviewNeeded) {
		this.reviewNeeded = reviewNeeded;
	}

	/**
	 * @return the approvalNeeded scheduled date
	 */
	public Date getApprovalNeeded() {
		return approvalNeeded;
	}

	/**
	 * @param approvalNeeded the approvalNeeded to set
	 */
	public void setApprovalNeeded(Date approvalNeeded) {
		this.approvalNeeded = approvalNeeded;
	}

}
