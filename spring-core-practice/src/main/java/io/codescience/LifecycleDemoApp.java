package io.codescience;

import io.codescience.service.MyService;
import io.codescience.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LifecycleDemoApp {

    @Autowired
    private MyService singletonService;

    @Autowired
    private MyService prototypeService;

    public void demonstrateLifecycle() {
        System.out.println("Singleton Service: " + singletonService.serve());
        System.out.println("Prototype Service: " + prototypeService.serve());
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        LifecycleDemoApp demoApp = context.getBean(LifecycleDemoApp.class);
        demoApp.demonstrateLifecycle();
    }
}