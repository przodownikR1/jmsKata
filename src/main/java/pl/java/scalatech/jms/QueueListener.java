package pl.java.scalatech.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

    @Override
    public void onMessage(final Message message) {
        if (!(message instanceof TextMessage)) { return; }
        final TextMessage textMessage = (TextMessage) message;
        try {
            logger.debug("onMessage \"{}\"", ((TextMessage) message).getText());
        } catch (final JMSException e) {
            logger.error("Exception while receiving message", e);
        }
    }
}