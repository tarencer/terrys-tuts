package com.terry.world.core_spring._1_SimpleTut;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;


public class DrawingApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/main/resources/simpleTut.xml"));
		Triangle triangle = (Triangle) factory.getBean("triangle");
		triangle.draw();
	}

}
