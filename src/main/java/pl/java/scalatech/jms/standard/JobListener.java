package pl.java.scalatech.jms.standard;

import javax.jms.Message;
import javax.jms.MessageListener;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class JobListener implements MessageListener {

    

    public void onMessage(Message message) {
        try {
            int jobId = message.getIntProperty("Job_ID");
            log.info("job {}",jobId);
    
        } catch (Exception e) {
            System.out.println("Worker caught an Exception");
        }
    }

	
}