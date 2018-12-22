package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {
		
		//load spring configuracion
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//retrieve a bean from the spring container
		Coach theCoach = context.getBean("boxingCoach",Coach.class);
		
		Coach alphaCoach = context.getBean("boxingCoach",Coach.class);
		
		//check if they are the same
		boolean result = (theCoach==alphaCoach);
		
		//print out the results
		System.out.println("\nApuntan a la misma zona de memoria: " + result);
		
		System.out.println("\nLocalizacion de memoria del AlphaCoach: " + theCoach);
		
		System.out.println("\nLocalizacion de memoria del AlphaCoach: " + alphaCoach);
		
		//close the context
		context.close();

	}

}
