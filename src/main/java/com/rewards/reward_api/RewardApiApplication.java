package com.rewards.reward_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RewardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardApiApplication.class, args);
	}

}
