package com.terry.world.core_spring._3_ConstructorInjection;

public class Triangle {
	private String type;
	private int x;
	private int y;
	
	public Triangle(String type) {
		this.type = type;
	}
	public Triangle(int x)
	{
		this.x = x;
	}
	public Triangle(String type,int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}
	public void draw()
	{
		System.out.println(getType()+" Triangle drawn at x:"+x+ " y:"+y);
	}
	
	public String getType() {
		return type;
	}
	
}
