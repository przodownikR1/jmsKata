package pl.java.scalatech.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

public class QueueSender {
    private static final Logger logger = LoggerFactory.getLogger(QueueSender.class);
    private final JmsTemplate jmsTemplate;
    private final String queueName;

    public QueueSender(final JmsTemplate jmsTemplate, String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    public void send(final String message) {
        logger.debug("start sending \"{}\"", message);
        jmsTemplate.convertAndSend(queueName, message);
        logger.debug("end sending \"{}\"", message);
    }
}