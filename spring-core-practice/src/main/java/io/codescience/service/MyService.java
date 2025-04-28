package io.codescience.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public String serve() {
        return "Service is serving:" + this.hashCode();
    }
}