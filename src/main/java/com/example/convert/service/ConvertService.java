package com.example.convert.service;

import com.example.convert.model.Convert;
import com.example.convert.model.ConvertionResponse;
import com.example.convert.model.ExchangeRate;
import com.example.convert.repository.ConvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConvertService {
    private ConvertRepository convertRepo;

    @Value("${exchangerate-api.key}")
    private String apiKey;

    @Autowired
    public ConvertService(ConvertRepository convertRepo){
        this.convertRepo=convertRepo;
    }

    public ConvertionResponse convertCurrency(String from, String to, float amount) {
        String apiUrl="https://v6.exchangerate-api.com/v6";
        String url = "%s/%s/pair/%s/%s/%s".formatted(apiUrl,apiKey,from,to,amount);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, ConvertionResponse.class, from, to, amount);
    }


    public List<Convert> getAllConverions(){
        return convertRepo.findAll();
    }

    public Convert getConversionById(Long id){
        return convertRepo.findById(id).orElse(null);
    }

    public void deleteAllConversions(){
        convertRepo.deleteAll();
    }
    public Long deleteConversionById(Long id){
        convertRepo.deleteById(id);
        return id;
    }
}
