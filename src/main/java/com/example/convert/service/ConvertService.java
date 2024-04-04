package com.example.convert.service;

import com.example.convert.model.Convert;
import com.example.convert.model.ConvertionResponse;
import com.example.convert.model.ExchangeRate;
import com.example.convert.repository.ConvertRepository;
import com.example.convert.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConvertService {
    private final ConvertRepository convertRepo;
    private final ExchangeRateRepository exchangeRateRepo;

    @Value("${exchangerate-api.key}")
    private String apiKey;

    @Autowired
    public ConvertService(ConvertRepository convertRepo,ExchangeRateRepository exchangeRateRepo){
        this.convertRepo=convertRepo;
        this.exchangeRateRepo=exchangeRateRepo;
    }

    public ConvertionResponse convertCurrency(String from, String to, Float amount) {
        String apiUrl="https://v6.exchangerate-api.com/v6";
        String url = "%s/%s/pair/%s/%s/%s".formatted(apiUrl,apiKey,from,to,amount);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, ConvertionResponse.class, from, to, amount);
    }

    public Convert createConversation(Long id,Float amountFrom){
        ExchangeRate rate = exchangeRateRepo.findById(id).orElse(null);
        Convert convert = new Convert(amountFrom, amountFrom * rate.getRate(), rate);
        convertRepo.save(convert);
        return convert;
    }

    public Convert updateConversion(Long id,Float amount){
        Convert convert = convertRepo.findById(id).orElse(null);
        convert.setAmountFrom(amount);
        convert.setAmountTo(amount * convert.getRate().getRate());
        return convert;
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
    public void deleteConversionById(Long id){
        convertRepo.deleteById(id);
    }
}
