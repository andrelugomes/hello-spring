package com.andrelugomes.springboot2subscriberrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBoot2SubscriberRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2SubscriberRestApplication.class, args);
	}
}
