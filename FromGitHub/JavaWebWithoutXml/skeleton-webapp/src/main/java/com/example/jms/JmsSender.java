package com.example.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * This sender will send a simple text message, but the commented return line shows how an object could be sent using 
 * a serializable, although it would be better to use a specific message object type, and then use a typed receiver class
 * @author micksear
 *
 */
public class JmsSender {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	 
    public void sendText(final String text) {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(text);
                //return session.createObjectMessage(new String());
            }
        });
    }

}
