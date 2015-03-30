package pl.java.scalatech.jms.standard;

import javax.jms.MessageProducer;
import javax.jms.TextMessage;

/**
 * @author przodownik
 *
 */
public class Producer extends Configuration {

	public void run() throws Exception {
		MessageProducer producer = session.createProducer(destination);
		for (int i = 0; i < 1000; ++i) {
			TextMessage message = session.createTextMessage("Job number: " + i);
			message.setIntProperty("Job_ID", i);
			producer.send(message);
		}
		producer.close();
	}

	public static void main(String[] args) throws Exception {
		Producer producer = new Producer();
		producer.init();
		producer.run();
		producer.cleanup();
	}

	@Override
	public String setDestination() {
		return "qA";
	}
}