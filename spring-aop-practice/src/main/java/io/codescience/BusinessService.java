package io.codescience;

import org.springframework.stereotype.Component;

@Component
public class BusinessService {
    public void performTask() {
        System.out.println("Performing business task");
    }
}