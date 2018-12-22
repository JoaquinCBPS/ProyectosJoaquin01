package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp2 {

	public static void main(String[] args) {
		
		//read spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//get the bean from spring container
		//Coach theCoach = context.getBean("tennisCoach",Coach.class);
		Coach theCoach = context.getBean("swimCoach",Coach.class);
		
		// call a method on the bean
		System.out.println(theCoach.getEmail());
		
		// call a method to get dailyFortune
		System.out.println(theCoach.getTeam());
		
		//close the context
		context.close();

	}

}
