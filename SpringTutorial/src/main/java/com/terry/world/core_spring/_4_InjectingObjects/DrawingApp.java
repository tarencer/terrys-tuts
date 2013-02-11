package com.terry.world.core_spring._4_InjectingObjects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DrawingApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("injectingObjects.xml");
		Triangle triangle = (Triangle) context.getBean("triangle");
		triangle.draw();
	}

}
