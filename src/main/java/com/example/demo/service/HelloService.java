package com.example.demo.service;

import com.example.demo.model.SampleData;
import com.example.demo.jms.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;

@Service
public class HelloService {
    @Autowired
    private ZoneId appZoneId;

    @Autowired
    private Producer producer;

    public SampleData testDate() {
        String time = "2018-01-01T00:00:00.000Z";
        Instant instant = Instant.parse(time);

        SampleData data = SampleData.builder()
                .text(String.format("timeZone=%s, time=%s)", appZoneId, "2018-01-01T00:00:00.000Z"))
                .zonedDateTime(ZonedDateTime.ofInstant(instant, appZoneId))
                .localDateTime(LocalDateTime.of(2018,Month.JANUARY,1,0,0,0))
                .localDate(LocalDate.of(2018, Month.JANUARY, 1))
                .localTime(LocalTime.of(0,0,0))
                .utilDate(Date.from(instant))
                .sqlDate(new java.sql.Date(Date.from(instant).getTime()))
                .build();

        producer.publish("queue1", data);
        producer.publish("queue2", data);

        return data;
    }
}
