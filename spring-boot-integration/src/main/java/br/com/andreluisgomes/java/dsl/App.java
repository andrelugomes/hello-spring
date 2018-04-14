package br.com.andreluisgomes.java.dsl;

import br.com.andreluisgomes.messaging.annotations.ChannelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@IntegrationComponentScan
public class App {

	public static void main(String[] args) {
    SpringApplication.run(App.class, args);
	}

	@Bean
	public MessageSource<?> integerMessageSource() {
		MethodInvokingMessageSource source = new MethodInvokingMessageSource();
		source.setObject(new AtomicInteger());
		source.setMethodName("getAndIncrement");
		return source;
	}
	@Bean
	public DirectChannel inputChannel() {
		return new DirectChannel();
	}
	@Bean
	public IntegrationFlow myFlow() {
		return IntegrationFlows.from(this.integerMessageSource(), c ->
						c.poller(Pollers.fixedRate(100)))
						.channel(this.inputChannel())
						.filter((Integer p) -> p > 0)
						.transform(Object::toString)
						.channel(MessageChannels.queue())
						.get();
	}
}
