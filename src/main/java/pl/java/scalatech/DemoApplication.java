package pl.java.scalatech;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.java.scalatech.config.JmsConfig;
import pl.java.scalatech.jms.QueueSender;

@SpringBootApplication
public class DemoApplication {

    static Random rand = new Random();
    
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(JmsConfig.class);
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        QueueSender queueSender = context.getBean(QueueSender.class);
        fillQueue(queueSender, threadPool, 1000 * 10);
        System.exit(0);
    }

    private static void fillQueue(final QueueSender queueSender, ExecutorService threadPool, int millis) {
        long aMinuteFromNow = System.currentTimeMillis() + millis;
        while (System.currentTimeMillis() < aMinuteFromNow) {
            threadPool.execute(new Runnable() {
                public void run() {

                    queueSender.send("przodownik "+ rand.nextInt());
                }
            });
        }
    }

}
