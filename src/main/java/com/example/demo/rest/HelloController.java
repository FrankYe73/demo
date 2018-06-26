package com.example.demo.rest;

import com.example.demo.model.SampleData;
import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService service;

    @GetMapping("/testDate")
    public SampleData testDate() {
        return service.testDate();
    }
}
