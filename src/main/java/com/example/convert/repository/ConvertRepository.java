package com.example.convert.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvertRepository {
    ResponseEntity<Object> convertCurrency(String from, String to, double amount);
}
