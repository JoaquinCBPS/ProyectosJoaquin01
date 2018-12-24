package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SwimJavaConfigDemoApp {

	public static void main(String[] args) {
		
		//read spring config file
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(MyLoggerConfig.class, SportConfig.class);
		
		//get the bean from spring container
		SwimCoach theCoach = context.getBean("swimCoach",SwimCoach.class);
		
		// call a method on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// call a method to get dailyFortune
		System.out.println(theCoach.getDailyFortune());
		
		// call team and email method
		System.out.println("email: "+ theCoach.getEmail());
		System.out.println("team: "+ theCoach.getTeam());
		//close the context
		context.close();

	}

}
