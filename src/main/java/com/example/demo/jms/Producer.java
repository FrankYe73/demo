package com.example.demo.jms;

import com.example.demo.model.SampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void publish(String destination, SampleData sampleData) {
        jmsTemplate.convertAndSend(destination, sampleData);
    }
}
