package com.example.convert.service;

import com.example.convert.repository.ConvertRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConvertService implements ConvertRepository {

    @Value("${Getgeoapi.api.key}")
    private String api_key;

    public ResponseEntity<Object> convertCurrency(String from, String to, double amount) {
        String apiUrl = "https://api.getgeoapi.com/v2/currency/convert";
        String url = "%s?api_key=%s&from=%s&to=%s&amount=%s&format=json".formatted(apiUrl,api_key,from,to,amount);
        RestTemplate restTemplate = new RestTemplate();

        return ResponseEntity.ok(restTemplate.getForObject(url,Object.class,from,to,amount));
    }
}