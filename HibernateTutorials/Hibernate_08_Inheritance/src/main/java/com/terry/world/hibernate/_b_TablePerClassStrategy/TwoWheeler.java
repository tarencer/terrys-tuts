/**
 * 
 */
package com.terry.world.hibernate._b_TablePerClassStrategy;

import javax.persistence.Entity;

/**
 * @author tools
 *
 */
@Entity
public class TwoWheeler extends Vehicle {
	private String handle;

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}
}
