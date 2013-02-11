package com.terry.world.core_spring._5_InnerBeans_Aliases_Idref;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DrawingApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("innerBeansAliasesIdref.xml");
//		Triangle triangle = (Triangle) context.getBean("triangle");
		// OR
//		Triangle triangle = (Triangle) context.getBean("triangle-name");
		// OR
		Triangle triangle = (Triangle) context.getBean("triangle-alias");
		triangle.draw();
	}

}
