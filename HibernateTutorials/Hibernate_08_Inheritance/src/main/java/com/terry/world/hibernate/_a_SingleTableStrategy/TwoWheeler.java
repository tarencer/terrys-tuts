/**
 * 
 */
package com.terry.world.hibernate._a_SingleTableStrategy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author tools
 *
 */
@Entity
@DiscriminatorValue("SmallVehicle")
public class TwoWheeler extends Vehicle {
	private String handle;

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}
}
