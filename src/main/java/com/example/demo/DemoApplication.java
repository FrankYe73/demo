package com.example.demo;

import com.example.demo.model.SampleData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.annotation.PostConstruct;
import java.time.*;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@EnableJms
public class DemoApplication {
    @Bean
    public ZoneId appZoneId() {
        String defaultId = TimeZone.getDefault().getID();
        return ZoneId.of(defaultId);

//        return ZoneId.of("UTC");
//        return ZoneId.of("America/New_York");
    }

    @Bean
    public TimeZone appTimeZone(ZoneId appZoneId) {
        return TimeZone.getTimeZone(appZoneId);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer addCustomBigDecimalDeserialization() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);

        return converter;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
    }

//    @PostConstruct
//    void started() {
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//    }
}
