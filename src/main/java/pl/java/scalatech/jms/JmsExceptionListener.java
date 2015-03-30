package pl.java.scalatech.jms;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

public class JmsExceptionListener implements ExceptionListener {
    @Override
    public void onException(JMSException e) {
        e.printStackTrace();
    }
}