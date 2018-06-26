package com.example.demo.jms;

import com.example.demo.model.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;

@Component
public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @JmsListener(destination = "queue1")
    public void receiveMessage(SampleData sampleData, @Headers Map<String, String> headers) {
        logger.info("Deserialize>>>>> {}", sampleData);
        //logger.info("headers    >>>>> {}", headers);
    }

    @JmsListener(destination = "queue2")
    public void receiveMessage(Message message, @Headers Map<String, String> headers) throws JMSException {
        //logger.info("activeMQ   >>>>> message={}, headers={}", message, headers);
        logger.info("Json       >>>>> {}", ((TextMessage)message).getText(), headers);
    }
}
