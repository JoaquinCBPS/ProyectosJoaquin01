package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Utilizamos spring con esta clase
 * @author cobos
 *
 */
public class HelloSpringApp {

	public static void main(String[] args) {
		
		//Paso 1:-> carga el archivo de configuracion
		ClassPathXmlApplicationContext context =
		new ClassPathXmlApplicationContext("applicationContext.xml");
		//Paso 2: -> obten el bean del spring container(del contexto) a partir del id que hemos definido en el contexto y la interfaz a partir de la cual la creamos
		//Creamos un objeto a partir del la clase definida
		Coach theCoach = context.getBean("myCoach", Coach.class);
		//Paso 3: -> llama a los métodos del bean
		System.out.println(theCoach.getDailyworkout());
		//let´s call our new method for fortunes 
		System.out.println(theCoach.getDailyFortune());
		//Paso 4: -> cierra contexto
		context.close();

	}

}
