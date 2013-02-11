package com.terry.world.core_spring._2_SetterInjection;

public class Triangle {
	private String type; 
	public void draw()
	{
		System.out.println(type+" Triangle drawn");
	}
	public void setType(String type) {
		this.type = type;
	}
}
