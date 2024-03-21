package com.example.convert.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConvertService {

    @Value("${exchangerate-api.key}")
    private String apiKey;

    public ResponseEntity<Object> convertCurrency(String from, String to, double amount) {
        String apiUrl="https://v6.exchangerate-api.com/v6";
        String url = "%s/%s/pair/%s/%s/%s".formatted(apiUrl,apiKey,from,to,amount);

        RestTemplate restTemplate = new RestTemplate();

        return ResponseEntity.ok(restTemplate.getForObject(url, Object.class, from, to, amount));
    }
}