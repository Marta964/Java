package com.example.convert.service;

import com.example.convert.model.Convert;
import com.example.convert.repository.ConvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.convert.model.ConvertionRersponse;
@Service
public class ConvertService {

    private ConvertRepository convertRepo;

    @Value("${exchangerate-api.key}")
    private String apiKey;

    @Autowired
    public ConvertService(ConvertRepository convertRepo){
        this.convertRepo=convertRepo;
    }
    public Convert convertation(Convert convertation){
        return convertRepo.save(convertation);
    }

    public ConvertionRersponse convertCurrency(String from, String to, double amount) {
        String apiUrl="https://v6.exchangerate-api.com/v6";
        String url = "%s/%s/pair/%s/%s/%s".formatted(apiUrl,apiKey,from,to,amount);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, ConvertionRersponse.class, from, to, amount);
    }

    public Long delete(Long id){
        convertRepo.deleteById(id);
        return id;
    }

}