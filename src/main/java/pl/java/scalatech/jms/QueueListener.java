package pl.java.scalatech.jms;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);
    @Getter
    private AtomicInteger count = new AtomicInteger();
    @Override
    public void onMessage(final Message message) {
        if (!(message instanceof TextMessage)) { return; }
        final TextMessage textMessage = (TextMessage) message;
        try {
            logger.debug("onMessage \"{}\"", ((TextMessage) message).getText());
            count.incrementAndGet();
        } catch (final JMSException e) {
            logger.error("Exception while receiving message", e);
        }
    }
}