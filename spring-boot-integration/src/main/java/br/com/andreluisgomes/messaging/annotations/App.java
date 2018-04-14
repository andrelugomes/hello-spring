package br.com.andreluisgomes.messaging.annotations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@IntegrationComponentScan //for @MessageGateway
@EnableScheduling
@Import(ChannelConfig.class)
public class App {

	public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);

    //GreeterService greeterService = ctx.getBean( "greeterServiceImpl", GreeterService.class );
    //greeterService.greet( "André!Luis!Gomes!" );
    //greeterService.greet( new Integer(1) );
	}
}
