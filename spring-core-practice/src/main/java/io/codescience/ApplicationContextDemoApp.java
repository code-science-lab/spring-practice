package io.codescience;

import io.codescience.config.AppConfig;
import io.codescience.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextDemoApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyService singletonService1 = context.getBean("singletonService", MyService.class);
        MyService prototypeService1 = context.getBean("prototypeService", MyService.class);
        MyService singletonService2 = context.getBean("singletonService", MyService.class);
        MyService prototypeService2 = context.getBean("prototypeService", MyService.class);

        System.out.println("Using ApplicationContext:");
        System.out.println("Singleton Service: " + singletonService1.serve());
        System.out.println("Singleton Service: " + singletonService2.serve());
        System.out.println("Prototype Service: " + prototypeService1.serve());
        System.out.println("Prototype Service: " + prototypeService2.serve());
    }
}