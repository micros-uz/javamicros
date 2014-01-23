package com.example.jms;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

/**
 * This message listener is typed, and will only receive TextMessage messages.
 * @author micksear
 *
 */
public class JmsReceiver implements SessionAwareMessageListener<TextMessage> {
	
	private static Logger log = LoggerFactory.getLogger(JmsReceiver.class);
	 
	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
		log.info("Received: " + message);
	}
}