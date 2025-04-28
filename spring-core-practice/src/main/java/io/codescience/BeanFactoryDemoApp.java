package io.codescience;

import io.codescience.config.AppConfig;
import io.codescience.service.MyService;
import io.codescience.ServiceConsumer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanFactoryDemoApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BeanFactory factory = context;

        MyService singletonService1 = (MyService) factory.getBean("singletonService");
        MyService singletonService2 = (MyService) factory.getBean("singletonService");

        MyService prototypeService1 = (MyService) factory.getBean("prototypeService");
        MyService prototypeService2 = (MyService) factory.getBean("prototypeService");

        System.out.println("Singleton Service 1: " + singletonService1.serve());
        System.out.println("Singleton Service 2: " + singletonService2.serve());
        System.out.println("Prototype Service 1: " + prototypeService1.serve());
        System.out.println("Prototype Service 2: " + prototypeService2.serve());

        System.out.println("Singleton Service 1 == Singleton Service 2: " + (singletonService1 == singletonService2));
        System.out.println("Prototype Service 1 == Prototype Service 2: " + (prototypeService1 == prototypeService2));

        ServiceConsumer serviceConsumer = context.getBean(ServiceConsumer.class);
        serviceConsumer.useService();
    }
}