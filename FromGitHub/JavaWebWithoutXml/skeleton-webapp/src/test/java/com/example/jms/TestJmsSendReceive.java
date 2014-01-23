package com.example.jms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.config.JmsTestProfile;
import com.example.jms.JmsSender;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JmsTestProfile.class})
@ActiveProfiles("test")
public class TestJmsSendReceive {

	@Autowired private JmsSender sender;
	
	@Test
	public void testSendReceive(){
		String hello = "Hello";
		
		sender.sendText(hello);
		
	}
}
