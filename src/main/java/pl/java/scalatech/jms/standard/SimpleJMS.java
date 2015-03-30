package pl.java.scalatech.jms.standard;

import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * @author przodownik
 *
 */
@Slf4j
public class SimpleJMS extends Configuration {

	public void start() throws Exception {

		MessageProducer producer = session.createProducer(destination);
		try {
			TextMessage message = session.createTextMessage();
			log.info("put message to queue....");
			message.setText("Hello World !");
			producer.send(message);
		} finally {
			producer.close();
		}

		MessageConsumer consumer = session.createConsumer(destination);
		try {
			TextMessage message = (TextMessage) consumer.receive();
			log.info("message : {}", message.getText());
		} finally {
			consumer.close();
		}
	}

	public static void main(String[] args) throws Exception {
		SimpleJMS jms = new SimpleJMS();

		jms.init();
		jms.start();
		jms.cleanup();

	}

	@Override
	public String setDestination() {
		return "queueA";
	}
}