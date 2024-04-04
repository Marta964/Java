package com.example.convert.service;

import com.example.convert.model.ExchangeRate;
import com.example.convert.repository.ExchangeRateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepo;

    @Autowired
    public ExchangeRateService(ExchangeRateRepository exchangeRateRepo){
        this.exchangeRateRepo=exchangeRateRepo;
    }

    @Value("${exchangerate-api.key}")
    private String apiKey;

    public ExchangeRate createExchangeRate(String from,String to){
        String apiUrl="https://v6.exchangerate-api.com/v6";
        String url = "%s/%s/pair/%s/%s".formatted(apiUrl,apiKey,from,to);

        RestTemplate restTemplate = new RestTemplate();
        ExchangeRate rate = restTemplate.getForObject(url,ExchangeRate.class,from,to);
        exchangeRateRepo.save(rate);
        return rate;
    }

    @Transactional
    public ExchangeRate updateExchangeRate(Long id,Float exchangeRate){
       ExchangeRate rate = exchangeRateRepo.findById(id).orElse(null);
       rate.setRate(exchangeRate);
       return rate;
    }

    public List<ExchangeRate> getAllExchangeRate(){
        return exchangeRateRepo.findAll();
    }

    public ExchangeRate getExchangeRateById(Long id){
        return exchangeRateRepo.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        exchangeRateRepo.deleteById(id);
    }

    public void deleteAllExchangeRate(){
        exchangeRateRepo.deleteAll();
    }
}
