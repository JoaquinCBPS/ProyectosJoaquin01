package com.luv2code.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {
	
	// create an array of strings
	private String[] data = {
			"Beaware of the wolf in sheep´s clothing",
			"Diligence is the motherFucker of good luck",
			"Go hard or go home",
			"Still undefeated",
			};
	// create a random number generator
	private Random myRandom = new Random();
	
	

	@Override
	public String getFortune() {
		// pick a random string from de array
		
		int index = myRandom.nextInt(data.length);
		
		String theFortune = data[index];
		return theFortune;
	}

}
