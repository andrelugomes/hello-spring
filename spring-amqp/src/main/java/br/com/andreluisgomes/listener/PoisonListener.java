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
public class PoisonListener implements MessageListener{
    private static final Logger LOG = LoggerFactory.getLogger(PoisonListener.class);

    public void onMessage(Message message) {

        LOG.info("RECEIVED END THROW");

        throw new RuntimeException("Boo! Error.");
    }
}
