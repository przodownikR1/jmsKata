package pl.java.scalatech.jms.standard;

import java.util.concurrent.TimeUnit;

import javax.jms.MessageConsumer;

public class Consumer extends Configuration {

	public void run() throws Exception {
		MessageConsumer consumer = session.createConsumer(destination);
		consumer.setMessageListener(new JobListener());
		TimeUnit.MINUTES.sleep(5);
		connection.stop();
		consumer.close();
	}

	public static void main(String[] args) throws Exception {
		Consumer producer = new Consumer();

		producer.init();
		producer.run();
		producer.cleanup();

	}

	@Override
	public String setDestination() {

		return "qA";
	}

}