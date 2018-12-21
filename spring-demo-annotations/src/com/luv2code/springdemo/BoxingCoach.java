package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component
public class BoxingCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Im Thython, and Im thuper thtrong ";
	}

}
