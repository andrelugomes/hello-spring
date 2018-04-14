package br.com.andreluisgomes;

import br.com.andreluisgomes.listener.MyListener;
import br.com.andreluisgomes.listener.PoisonListener;
import org.aopalliance.aop.Advice;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.policy.SimpleRetryPolicy;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by agomes on 12/07/16.
 */
@EnableRabbit
@Configuration
public class AMQPConfig {

    private static final String QUEUE_TEST = "queue-test";
    private static final String QUEUE_TEST_DLQ = "queue-test-dlq";
    private static final String TOPIC_EXCHANGE_TEST = "topic-exchange-test";
    private static final java.lang.String TOPIC_EXCHANGE_DEAD = "topic-exchange-dead";

    @Bean
    public ConnectionFactory connectionFactory(@Value("${rabbit.host}") String brokerAddresses) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("user");
        connectionFactory.setPassword("password");
        connectionFactory.setAddresses(brokerAddresses);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    RetryOperationsInterceptor retryOperationsInterceptor(
            @Value("${listener.max.retry}") int maxRetry,
            @Value("${listener.retry.delay}") long retryDelay,
            @Value("${listener.retry.multiplier}") double retryMultiplier,
            @Value("${listener.retry.max.interval}") long maxInterval,
            RabbitTemplate rabbitTemplate) {

        Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
        retryableExceptions.put(ValidationException.class, false);
        retryableExceptions.put(IOException.class, true);
        retryableExceptions.put(RuntimeException.class, true);
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(maxRetry, retryableExceptions, true);

        return RetryInterceptorBuilder.stateless()
                .retryPolicy(simpleRetryPolicy)
                .backOffOptions(retryDelay, retryMultiplier, maxInterval)
                .recoverer(new RepublishMessageRecoverer(rabbitTemplate, "", QUEUE_TEST_DLQ))
                .build();
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory, MyListener myListener,
                                                            RetryOperationsInterceptor retryOperationsInterceptor) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_TEST);
        container.setConcurrentConsumers(10);
        //FALSE : will not requeued
        container.setDefaultRequeueRejected(false);
        //container.setMessageListener(myListener);
        //container.setMessageListener(new MessageListenerAdapter(exampleListener()));
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainerRedelivery(ConnectionFactory connectionFactory, PoisonListener poisonListener,
                                                            RetryOperationsInterceptor retryOperationsInterceptor) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_TEST);
        container.setConcurrentConsumers(10);
        container.setDefaultRequeueRejected(true);
        //container.setMessageConverter(jsonMessageConverter());
        container.setAdviceChain(new Advice[] {retryOperationsInterceptor});
        container.setMessageListener(poisonListener);
        return container;
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new JsonMessageConverter();
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_TEST, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_TEST);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_TEST);
    }

    @Bean
    public Queue queueTestDlq() {
        return new Queue(QUEUE_TEST_DLQ, true);
    }

    @Bean
    TopicExchange exchangeDead() {
        return new TopicExchange(TOPIC_EXCHANGE_DEAD);
    }

    @Bean
    Binding bindingDlq(Queue queueTestDlq, TopicExchange exchangeDead) {
        return BindingBuilder.bind(queueTestDlq).to(exchangeDead).with(QUEUE_TEST_DLQ);
    }
}
