package br.com.caelum.camel;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

	@Autowired
	private CamelContext context;

	@PostConstruct
	public void init() throws Exception {
		context.addComponent("activemq", ActiveMQComponent.activeMQComponent("tcp://192.168.99.100:61616"));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
