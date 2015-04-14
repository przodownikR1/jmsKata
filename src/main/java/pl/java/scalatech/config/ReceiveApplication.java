package pl.java.scalatech.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import pl.java.scalatech.jms.Receiver;

@Configuration
@EnableJms
public class ReceiveApplication {

	public final static String DESC = "mailbox";
	
	@Bean
	Receiver receiver() {
	    System.err.println("-----------------receiver");
		return new Receiver();
	}
	
}