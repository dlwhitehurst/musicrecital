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

import java.io.Serializable;


/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode().
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version $Id$
 */
public abstract class BaseObject implements Serializable {    

    /**
	 * unique serial identifier, will probably never be used, not sure 
	 * considering class will never be instantiated. Eclipse warns because
	 * Serializable is implemented but instantiated classes should not
	 * have to implement Serializable.
	 *  
	 */
	private static final long serialVersionUID = -1766357336987264790L;

	/**
     * Returns a multi-line String with key=value pairs.
     * @return a String representation of this class.
     */
    public abstract String toString();

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     * @param o object to compare to
     * @return true/false based on equality tests
     */
    public abstract boolean equals(Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() important" for more information:
     * http://www.hibernate.org/109.html
     * @return hashCode
     */
    public abstract int hashCode();
}
