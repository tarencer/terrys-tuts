package com.terry.world.core_spring._3_ConstructorInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DrawingApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("constructorInjection.xml");
		Triangle triangle = (Triangle) context.getBean("triangle_String");
		triangle.draw();
		
		triangle = (Triangle) context.getBean("triangle_String_int_int");
		triangle.draw();
	}

}
