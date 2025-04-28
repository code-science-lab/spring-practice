package io.codescience;

import io.codescience.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceConsumer {
    private final MyService singletonService;

    @Autowired
    public ServiceConsumer(MyService singletonService) {
        this.singletonService = singletonService;
    }

    public void useService() {
        System.out.println("Using Singleton Service: " + singletonService.serve());
    }
}