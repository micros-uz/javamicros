package com.example.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.jms.JmsReceiver;
import com.example.jms.JmsSender;

/**
 * @see http://spring.io/blog/2011/02/14/spring-3-1-m1-introducing-profile/
 *
 */
@Configuration
@EnableTransactionManagement
@Profile("test")
public class JmsTestProfile {
	
	public static final String TARGET = "test.queue";
	
	@Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        
        jmsTemplate.setDefaultDestination(new ActiveMQTopic(TARGET));
        
        //To use a topic rather than a queue, switch the above line to this one:
        //jmsTemplate.setDefaultDestination(new ActiveMQQueue(TARGET));
        
        jmsTemplate.setConnectionFactory(connectionFactory());
        
        //Enable transactions on the JMS system
        jmsTemplate.setSessionTransacted(true);
        
        //Turn this into a topic rather than a queue
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }
	
	/**
	 * For testing, use an embedded broker service
	 * @return
	 * @throws Exception
	 */
	@Bean
	public BrokerService embeddedBroker() throws Exception{
		BrokerService broker = new BrokerService();
		 
		// configure the broker
		broker.addConnector("tcp://localhost:61616");
		 
		broker.start();
		return broker;
	}
	
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        //activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        activeMQConnectionFactory.setBrokerURL("failover:(tcp://localhost:61616,tcp://localhost:61616)");
        
        return activeMQConnectionFactory;
    }
    
    @Bean
    public JmsSender jmsSender() {
        return new JmsSender();
    }
    
    @Bean
    public JmsReceiver jmsReceiver() {
        return new JmsReceiver();
    }
    
    @Bean
    public DefaultMessageListenerContainer messageListenerContainer() {
        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory());
        messageListenerContainer.setDestinationName("test.queue");
        messageListenerContainer.setMessageListener(jmsReceiver());
        
        return messageListenerContainer;
    }
    
}