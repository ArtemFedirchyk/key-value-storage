package com.keyvaluestorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class EntryPoint extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplication(EntryPoint.class).run(args);
    }
}
