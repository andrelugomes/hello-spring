package br.com.andreluisgomes.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by agomes on 12/07/16.
 */
@Component
public class MyProducer {

    private static final Logger LOG = LoggerFactory.getLogger(MyProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 2000)
    public void produce(){
        LOG.info("SEND...");
        rabbitTemplate.convertAndSend("topic-exchange-test","queue-test", "Hello from RabbitMQ!");
    }
}
