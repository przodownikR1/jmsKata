package pl.java.scalatech.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig2 {
    
  
/*
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setConcurrency("1-3");
    return factory;
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(ConnectionFactory connectionFactory) {
    JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
    JmsMessagingTemplate jmsMessagingTemplate = new JmsMessagingTemplate(jmsTemplate);
    return jmsMessagingTemplate;
    }


    @PostConstruct
    public void postConstruct() throws Exception {
    brokerService.start();
    }

    @PreDestroy
    public void preDestroy() throws Exception {
    brokerService.stop();
    }

    @Bean
    public BrokerService brokerService() throws Exception {
    BrokerService brokerService = new BrokerService();

    brokerService.setUseJmx(false);
    brokerService.setPersistent(true);

    SystemUsage systemUsage = brokerService.getSystemUsage();
    systemUsage.getStoreUsage().setLimit(1024 * 1024 * 128);
    systemUsage.getTempUsage().setLimit(1024 * 1024 * 128);

    return brokerService;
    }
    
    @Bean
    public ConnectionFactory connectionFactory(BrokerService brokerService) {
    ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
    return cf;
    }*/
}
