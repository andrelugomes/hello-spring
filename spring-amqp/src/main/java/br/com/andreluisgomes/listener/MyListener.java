package br.com.andreluisgomes.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Created by agomes on 12/07/16.
 */
@Component
public class MyListener implements MessageListener{
    private static final Logger LOG = LoggerFactory.getLogger(MyListener.class);

    public void onMessage(Message message) {

        LOG.info("RECEIVED : "+ new String(message.getBody()));
    }
}
