package br.com.andrelugomes.springboot2connectionpoolmonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBoot2ConnectionPoolMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2ConnectionPoolMonitoringApplication.class, args);
	}
}
