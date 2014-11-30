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

package org.musicrecital.model.repertoire;

import java.util.List;

import org.musicrecital.model.accompanist.Accompanist;
import org.musicrecital.model.performer.Performer;

/**
 * @author <a href="mailto:dlwhitehurst@me.com">David L. Whitehurst</a>
 *
 */
public class Ensemble extends Piece {

	private Long id;
	
	private int bpm;
	
	private String timeSignature;
	
	private String key;
	
	private List<Performer> performers;
	
	private List<Accompanist> accompanists;

	/* (non-Javadoc)
	 * @see org.musicrecital.model.repertoire.Piece#getComposers()
	 */
	@Override
	public List<String> getComposers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.repertoire.Piece#setComposers(java.util.List)
	 */
	@Override
	public void setComposers(List<String> composers) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.repertoire.Piece#setArrangers(java.util.List)
	 */
	@Override
	public void setArrangers(List<String> arrangers) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.repertoire.Piece#setSongwriters(java.util.List)
	 */
	@Override
	public void setSongwriters(List<String> songwriters) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.repertoire.Piece#getSongwriters()
	 */
	@Override
	public List<String> getSongwriters() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.repertoire.Piece#getArrangers()
	 */
	@Override
	public List<String> getArrangers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.repertoire.Piece#getTitle()
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.repertoire.Piece#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.repertoire.Piece#getYear()
	 */
	@Override
	public int getYear() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.musicrecital.model.repertoire.Piece#setYear(int)
	 */
	@Override
	public void setYear(int year) {
		// TODO Auto-generated method stub
		
	}

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

}
