package com.terry.world.core_spring._5_InnerBeans_Aliases_Idref;

public class Triangle {
	private String startPoint;
	private Point pointA;
	private Point pointB;
	private Point pointC;
	
	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startingPoint) {
		this.startPoint = startingPoint;
	}

	public Point getPointA() {
		return pointA;
	}
	
	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}
	
	public Point getPointB() {
		return pointB;
	}
	
	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}
	
	public Point getPointC() {
		return pointC;
	}
	
	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}
	
	public void draw()
	{
		System.out.println("Start point is : " + getStartPoint());
		System.out.println("Point A = ("+getPointA().getX()+"\t, "+getPointA().getY()+")");
		System.out.println("Point B = ("+getPointB().getX()+"\t, "+getPointB().getY()+")");
		System.out.println("Point C = ("+getPointC().getX()+"\t, "+getPointC().getY()+")");
	}
}
