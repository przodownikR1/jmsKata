package pl.java.scalatech.config;

import javax.jms.ExceptionListener;
import javax.jms.MessageListener;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import pl.java.scalatech.jms.JmsExceptionListener;
import pl.java.scalatech.jms.QueueListener;
import pl.java.scalatech.jms.QueueSender;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:jms.properties")
public class JmsConfig {

    @Value("${jms.userName}")
    private String userName;

    @Value("${jms.password}")
    private String password;

    @Value("${jms.brokerUrl}")
    private String brokerUrl;

    @Value("${jms.sessionCacheSize}")
    private int sessionCacheSize;

    @Value("${jms.queueName}")
    private String queueName;

    @Value("${jms.listenerContainer.concurrency}")
    private String listenerContainerConcurrency;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholder() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Profile("embedded")
    @Bean(initMethod = "start", destroyMethod = "stop")
    public BrokerService brokerService() throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setBrokerName("testBroker");
        brokerService.setPersistent(false);
        brokerService.setUseShutdownHook(true);
        brokerService.setEnableStatistics(true);
        brokerService.addConnector("tcp://localhost:61616");
        return brokerService;
    }

    private ExceptionListener jmsExceptionListener() {
        return new JmsExceptionListener();
    }

    public CachingConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(userName, password, brokerUrl);
       // activeMQConnectionFactory.setAlwaysSessionAsync(true);
       // activeMQConnectionFactory.setAlwaysSyncSend(true);
        
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(activeMQConnectionFactory);
        cachingConnectionFactory.setExceptionListener(jmsExceptionListener());
        cachingConnectionFactory.setSessionCacheSize(sessionCacheSize);
        return cachingConnectionFactory;
    }

    @Bean
    public JmsTransactionManager jmsTransactionManager() {
        return new JmsTransactionManager(this.connectionFactory());
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jms = new JmsTemplate();
        jms.setConnectionFactory(connectionFactory());
        jms.setDefaultDestination(orderQueue());
        jms.setReceiveTimeout(100L);
        
        jms.setSessionTransacted(true);
        return jms;
    }

    @Bean
    public ActiveMQQueue orderQueue() {
        return new ActiveMQQueue(queueName);
    }
    
    @Bean
    public ActiveMQQueue mailbox() {
        return new ActiveMQQueue("mailbox");
    }
    

    @Bean
    public QueueSender queueSender() {
        return new QueueSender(jmsTemplate(), queueName);
    }
    @Bean
    public MessageListener queueListener() {
        return new QueueListener();
    }

    @Bean
    public AbstractMessageListenerContainer listenerContainer() {
        final DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
        defaultMessageListenerContainer.setConcurrency(listenerContainerConcurrency);
        defaultMessageListenerContainer.setDestinationName(queueName);
        defaultMessageListenerContainer.setMessageListener(queueListener());
        
        return defaultMessageListenerContainer;
    }

}
