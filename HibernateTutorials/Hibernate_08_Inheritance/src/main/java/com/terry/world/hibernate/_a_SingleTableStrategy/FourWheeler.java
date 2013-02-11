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
@DiscriminatorValue("BigVehicle")
public class FourWheeler extends Vehicle {
	private String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}
	
}
