package com.example.convert.service;

import com.example.convert.model.Convert;
import com.example.convert.model.ConversionResponse;
import com.example.convert.model.ExchangeRate;
import com.example.convert.repository.ConvertRepository;
import com.example.convert.repository.ExchangeRateRepository;
import jakarta.transaction.Transactional;
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
    public ConvertService(final ConvertRepository convertRepo,
                          final ExchangeRateRepository exchangeRateRepo) {
        this.convertRepo = convertRepo;
        this.exchangeRateRepo = exchangeRateRepo;
    }


    public Convert createConversation(final Long id, final Float amountFrom) {
        ExchangeRate rate = exchangeRateRepo.findById(id).orElse(null);
        Convert convert = new Convert();
        if (rate != null) {
        convert.setAmountFrom(amountFrom);
        convert.setAmountTo(amountFrom * rate.getRate());
        convert.setRate(rate);
        convertRepo.save(convert);
        }
        return convert;
    }

    @Transactional
    public Convert updateConversion(final Long id, final Float amount) {
        Convert convert = convertRepo.findById(id).orElse(null);
        if (convert != null) {
            convert.setAmountFrom(amount);
            convert.setAmountTo(amount * convert.getRate().getRate());
        }
        return convert;
    }

    public List<Convert> getAllConversions() {
        return convertRepo.findAll();
    }

    public Convert getConversionById(final Long id) {
        return convertRepo.findById(id).orElse(null);
    }

    public void deleteAllConversions() {
        convertRepo.deleteAll();
    }
    public void deleteConversionById(final Long id) {
        convertRepo.deleteById(id);
    }
}
