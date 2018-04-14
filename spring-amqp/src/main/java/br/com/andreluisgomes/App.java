package br.com.andreluisgomes;

import org.springframework.boot.SpringApplication   ;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Import({AMQPConfig.class})
public class App {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(App.class, args);
	}
}