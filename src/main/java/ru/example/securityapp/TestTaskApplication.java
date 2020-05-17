package ru.example.securityapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TestTaskApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TestTaskApplication.class);
    }

}

//@SpringBootApplication
//public class TestTaskApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(TestTaskApplication.class, args);
//    }
//
//}
