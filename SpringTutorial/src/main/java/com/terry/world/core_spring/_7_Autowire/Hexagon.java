package com.terry.world.core_spring._7_Autowire;

public class Hexagon {
	private Triangle triangle;

	public Triangle getTriangle() {
		return triangle;
	}

	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}
	
	public Hexagon(Triangle triangle)
	{
		this.triangle = triangle;
	}
	
	public Hexagon()
	{
	}
	
	public void draw()
	{
		triangle.draw();
	}
}
