package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleData {
    private String text;
    private ZonedDateTime zonedDateTime;
    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private LocalTime localTime;
    private Date utilDate;
//    @JsonFormat(pattern = "yyyy-MM-dd'T':HH:mm:ss.SSSz")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date sqlDate;
}