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
public class FourWheeler extends Vehicle {
	private String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}
	
}
