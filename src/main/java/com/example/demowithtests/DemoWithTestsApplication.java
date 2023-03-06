package com.example.demowithtests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DemoWithTestsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWithTestsApplication.class, args);
    }

}
