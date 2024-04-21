package com.example.convert.service;

import com.example.convert.cache.CacheManager;
import com.example.convert.model.ExchangeRate;
import com.example.convert.repository.ExchangeRateRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepo;
    private final CacheManager<String, Object> cache;
    private static final Logger LOG =
            LoggerFactory.getLogger(ExchangeRateService.class);
    private static final String RATE = "rate";

    /**
     * Возвращает все курсы по базовой и котируемой валютах.
     *
     * @param from Базовая валюта.
     * @param to Котируемая валюта.
     * @return все курсы этих валют.
     */
    public List<ExchangeRate> getExchangeRateByPair(final String from, final String to) {
        return exchangeRateRepo.getExchangeRateByPair(from, to);
    }

    /**
     *Запрашивает курс валют из API и сохраняет курс в базу данных и кэш.
     *
     * @param from базовая валюта.
     * @param to котируемая валюта.
     * @return курс валют.
     */
    public ExchangeRate createExchangeRate(final String from, final String to) {
        String apiKey ="d8f0d82781014c6a4bddaec0";
        String apiUrl = "https://v6.exchangerate-api.com/v6";
        String url = "%s/%s/pair/%s/%s".formatted(apiUrl, apiKey, from, to);

        LOG.info("Create new exchange rate");
        RestTemplate restTemplate = new RestTemplate();
        ExchangeRate rate = restTemplate.getForObject(url, ExchangeRate.class, from, to);
        if (rate != null) {
        exchangeRateRepo.save(rate);
        cache.put(RATE + rate.getId().toString(), rate);}
        return rate;
    }

    public ExchangeRate create(ExchangeRate rate){
        exchangeRateRepo.save(rate);
        return rate;
    }

    public List<ExchangeRate> createBulkExchangeRates(final List<ExchangeRate> rates) {
        List<ExchangeRate> existingRates = rates.stream().
                filter(rate -> exchangeRateRepo.existsByRate(rate.getRate()) && exchangeRateRepo.existsByFrom(rate.getFrom()) && exchangeRateRepo.existsByTo(rate.getTo()))
                .toList();
        List<ExchangeRate> createdRates = rates.stream()
                .filter(rate ->
                        !exchangeRateRepo.existsByRate(rate.getRate()) || !exchangeRateRepo.existsByFrom(rate.getFrom()) || !exchangeRateRepo.existsByTo(rate.getTo()))
                .map(exchangeRateRepo::save).toList();
        existingRates.forEach(rate->LOG.warn("Rate with rate '{}' already existed",rate.getRate()));
        return  createdRates;
    }


    /**
     * Обновляет курс о городе по id.
     *
     * @param id курса.
     * @param exchangeRate Новый курс.
     * @return Обновленный курс валют.
     */
    @Transactional
    public ExchangeRate updateExchangeRate(final Long id, final Float exchangeRate) {
        LOG.info("Updating exchange rate with id:{}", id);
        ExchangeRate rate = exchangeRateRepo.findById(id).orElseThrow(null);
       if (rate != null) {
           rate.setRate(exchangeRate);
       }
       cache.put(RATE + id, rate);
       return rate;
    }

    /**
     * Возвращает все курсы.
     *
     * @return Список всех городов.
     */
    public List<ExchangeRate> getAllExchangeRate() {
        LOG.info("Fetching all exchange rates");
        return exchangeRateRepo.findAll();
    }

    /**
     * Возвращает курс по id, сначала ищет в кэше, затем в базе данных.
     *
     * @param id курса.
     * @return курс с указанным id.
     */
    public ExchangeRate getExchangeRateById(final Long id) {
        LOG.info("Fetching exchange rate by id:{}", id);
        Object cacheData = cache.get(RATE + id.toString());
        if (cacheData != null) { return (ExchangeRate) cacheData;
        } else {
            ExchangeRate rate = exchangeRateRepo.findById(id).orElse(null);
            if (rate != null) {
                cache.put(RATE + id, rate);
            }
            return rate;
        }
    }

    /**
     * Удаляет курс по id из базы данных и кэша.
     *
     * @param id курса.
     */
    public void deleteById(final Long id) {
        LOG.info("Deleting exchange rate by id:{}", id);
        boolean exist = exchangeRateRepo.existsById(id);
        if (!exist) {throw new EntityNotFoundException("dish id: " + id + "is not deleted(does not exist)");
        }
        exchangeRateRepo.deleteById(id);
        cache.remove(RATE + id);
    }

    /**
     * Удаляет все курсы из базы данных и кэша.
     */
    public void deleteAllExchangeRate() {
        LOG.info("Deleting all exchange rates");
        exchangeRateRepo.deleteAll();
        cache.clear();
    }
}
