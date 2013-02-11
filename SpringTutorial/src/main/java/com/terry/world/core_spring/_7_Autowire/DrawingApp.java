package com.terry.world.core_spring._7_Autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DrawingApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("autowire.xml");
		Triangle triangle = (Triangle) context.getBean("triangle");
		triangle.draw();
		
		Hexagon hexagon = (Hexagon) context.getBean("hexagon_type");
		hexagon.draw();
		
		hexagon = (Hexagon) context.getBean("hexagon_constructor");
		hexagon.draw();
	}

}
