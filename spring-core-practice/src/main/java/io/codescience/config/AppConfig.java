package io.codescience.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import io.codescience.service.MyService;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan("io.codescience")
public class AppConfig {

    @Bean
    @Scope("singleton")
    public MyService singletonService() {
        return new MyService();
    }

    @Bean
    @Scope("prototype")
    public MyService prototypeService() {
        return new MyService();
    }
}