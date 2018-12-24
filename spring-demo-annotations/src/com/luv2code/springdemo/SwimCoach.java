package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public class SwimCoach implements Coach {
	
	private FortuneService fortuneService;
	
	public SwimCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	
	@Value("${foo.email}")
	private String email;
	    
	
	@Value("${foo.team}")
	private String team;

	@Override
	public String getDailyWorkout() {
		return "Swim 1000 Kilometers as a warm up";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return fortuneService.getFortune();
	}

	
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	
	public String getTeam() {
		// TODO Auto-generated method stub
		return team;
	}

}
