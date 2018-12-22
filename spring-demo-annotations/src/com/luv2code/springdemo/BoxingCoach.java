package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BoxingCoach implements Coach {
	//Nos evitamos el set para este campo
	@Autowired
	@Qualifier("fileFortuneService")
	private FortuneService fortuneService;
	
	// define default constructor
	public BoxingCoach() {
		System.out.println(">> inside default constructor");
	}
	/*
	// define a setter method
	@Autowired
	public void doSomeCrazyStuff(FortuneService theFortuneService) {
		System.out.println(">> inside setFortuneService() method");
		fortuneService = theFortuneService;
	}*/

	/*public BoxingCoach(FortuneService thefortuneService) {
		fortuneService = thefortuneService;
	}*/
	
	

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Im Thython, and Im thuper thtrong ";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return fortuneService.getFortune();
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTeam() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
