package com.terry.world.core_spring._9_AwareInterfaces;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Triangle implements ApplicationContextAware,BeanNameAware{
	public void draw()
	{
		System.out.println("Triangle drawn");
	}

	public void setBeanName(String beanName) {
		System.out.println("Bean Name :"+beanName);
		
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		System.out.println(context.getDisplayName());
	}
}
