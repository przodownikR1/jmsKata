package pl.java.scalatech.jms;

import lombok.extern.slf4j.Slf4j;

import org.springframework.jms.annotation.JmsListener;

import pl.java.scalatech.config.ReceiveApplication;
@Slf4j
public class Receiver {
	
	@JmsListener(destination=ReceiveApplication.DESC,concurrency="2")
	public void receiveMessage(String message) {
		log.info("++++      Receiver : {}",message);
	}

}