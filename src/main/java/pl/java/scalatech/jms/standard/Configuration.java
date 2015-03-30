package pl.java.scalatech.jms.standard;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public abstract class Configuration {

	protected final String connectionUri = "tcp://localhost:61616";
	protected ActiveMQConnectionFactory connectionFactory;
	protected Connection connection;
	protected Session session;
	protected Destination destination;

	public abstract String setDestination();

	public void init() throws Exception {
		connectionFactory = new ActiveMQConnectionFactory(connectionUri);
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue(setDestination());
	}

	public void cleanup() throws Exception {
		if (connection != null) {
			connection.close();
		}
	}
}
