package io.codescience.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {
    public String getData() {
        return "Data from repository";
    }
}