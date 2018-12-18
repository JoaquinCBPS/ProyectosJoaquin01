package com.luv2code.springdemo;

public class BaseballCoach implements Coach {
	
	private FortuneService fortuneService;
	
	public BaseballCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	
	public String getDailyworkout() {
		return"Spend 30 minutes on the batting practice";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
