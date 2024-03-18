package com.example.convert.service;


import com.example.convert.repository.ConvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConvertService {


    private ConvertRepository convertRepo;

    @Autowired
    public ConvertService(ConvertRepository convertRepo) {
        this.convertRepo = convertRepo;
    }

    @Value("${Getgeoapi.api.key}")
    private String apiKey;

    public ResponseEntity<Object> convertCurrency(String from, String to, double amount) {
        String apiUrl = "https://api.getgeoapi.com/v2/currency/convert";
        String url = "%s?api_key=%s&from=%s&to=%s&amount=%s&format=json".formatted(apiUrl, apiKey, from, to, amount);

        RestTemplate restTemplate = new RestTemplate();

        return ResponseEntity.ok(restTemplate.getForObject(url, Object.class, from, to, amount));
    }


}