package pl.java.scalatech.contoller;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class BooTMessage {
    
    
    
    
    @Autowired
    JmsMessagingTemplate messagingTemplate;

    @RequestMapping(value = "/message")
    String hello() {
        Message<String> message = MessageBuilder
                .withPayload("Hello!")
                .build();
        messagingTemplate.send("queueB", message);
        return "Hello World!";
    }

    @JmsListener(destination = "queueB")
    void handleMessage(Message<String> message) {
        log.info("received!  -> {}", message);
    }
}
