package io.codescience.component;

import org.springframework.stereotype.Component;

@Component
public class MyComponent {
    public String execute() {
        return "Component is executing";
    }
}