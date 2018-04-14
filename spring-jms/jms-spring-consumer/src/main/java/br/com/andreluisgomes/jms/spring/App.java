package br.com.andreluisgomes.jms.spring;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJms
@EnableScheduling
@ComponentScan(basePackages="br.com.andreluisgomes.jms.spring")
public class App {

    private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        activeMQConnectionFactory.setNonBlockingRedelivery(true);
		return activeMQConnectionFactory;
    }
    
    @Bean
    public RedeliveryPolicy redeliveryPolicy(){
    	RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
    	redeliveryPolicy.setQueue("*");
    	redeliveryPolicy.setMaximumRedeliveries(2);
    	redeliveryPolicy.setRedeliveryDelay(15000);
    	return redeliveryPolicy;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
