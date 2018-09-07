package com.example.springboot2kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConsumer {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumer.class, args);
	}
}
