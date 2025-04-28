package io.codescience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "io.codescience")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
} 