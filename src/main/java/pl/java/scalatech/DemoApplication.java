package pl.java.scalatech;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import pl.java.scalatech.config.JmsConfig;
import pl.java.scalatech.config.ReceiveApplication;
import pl.java.scalatech.jms.QueueListener;
import pl.java.scalatech.jms.QueueSender;
@Slf4j
@SpringBootApplication
public class DemoApplication {

    static Random rand = new Random();
    static AtomicInteger count = new AtomicInteger();

    
  
    
    
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DemoApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(JmsConfig.class);
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 5);
        QueueSender queueSender = context.getBean(QueueSender.class);
        QueueListener ql = context.getBean(QueueListener.class);
       
        DefaultMessageListenerContainer listener = (DefaultMessageListenerContainer) context.getBean(DefaultMessageListenerContainer.class);
       
            //fillQueue(queueSender, threadPool, 1000 * 1);
            log.info("fill messages :  {}", count);
            log.info("ConcurrentConsumers : {}", listener.getConcurrentConsumers());
            log.info("ActiveConsumerCount : {}", listener.getActiveConsumerCount());
            log.info("MaxConcurrentConsumers : {}", listener.getMaxConcurrentConsumers());
            System.err.println(ql.getCount());
            mailBoxSender(context);
            Thread.sleep(1000);
        System.exit(0);
    }

     static void mailBoxSender(ApplicationContext context){
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
               return session.createTextMessage("przodownik_test");
            }
         };
         JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
         
         jmsTemplate.send(ReceiveApplication.DESC, messageCreator);
         
    }
    
    private static void fillQueue(final QueueSender queueSender, ExecutorService threadPool, int millis) {
        long time = System.currentTimeMillis() + millis;
        
        while (System.currentTimeMillis() < time) {
            threadPool.execute(new Runnable() {
                public void run() {
                    queueSender.send("przodownik " + rand.nextInt());
                    count.incrementAndGet();
                }
            });
        }
        
    }

}
