package com.luv2code.springdemo;

public class HappyFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		
		int random = (int)(Math.random()*4);
		
		String[] randomFortune = {"Its not your day","better go home", "You´re going to die today","Good Luck", "today is your fucking day"};
		
		
		return randomFortune[random];
	}

}
