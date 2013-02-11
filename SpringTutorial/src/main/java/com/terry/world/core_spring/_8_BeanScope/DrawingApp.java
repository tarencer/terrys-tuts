package com.terry.world.core_spring._8_BeanScope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DrawingApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beanScope.xml");
		Triangle triangleOne = (Triangle) context.getBean("triangle");
		System.out.println("triangle one : "+triangleOne);
		Triangle triangleTwo = (Triangle) context.getBean("triangle");
		System.out.println("triangle two : "+triangleTwo); 
		
		assert triangleOne != triangleTwo;
	}

}
