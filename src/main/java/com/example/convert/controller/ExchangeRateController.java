package com.example.convert.controller;

import com.example.convert.model.ExchangeRate;
import com.example.convert.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ExchangeRate",description = "Managing exchange rates.")
@RestController
@RequestMapping("/exchangerate")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(final ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @PostMapping()
    public ResponseEntity<ExchangeRate> createExchangeRate(@RequestParam final String from, @RequestParam final String to) {
        ExchangeRate rates = exchangeRateService.createExchangeRate(from, to);
        return ResponseEntity.ok(rates);
    }
    @PostMapping("/body")
    public ExchangeRate create(@RequestBody ExchangeRate rate) {
        return exchangeRateService.create(rate);
    }

    @PostMapping("/bulk")
    public List<ExchangeRate>createBulkExchangeRates(@RequestBody final List<ExchangeRate> rates) {
        return exchangeRateService.createBulkExchangeRates(rates);
    }

    @PutMapping("/{id}")
    public ExchangeRate updateExchangeRate(@PathVariable final Long id, @RequestParam final Float exchangeRate) {
        return exchangeRateService.updateExchangeRate(id, exchangeRate);
    }

    @GetMapping("/pair")
    public List<ExchangeRate> getExchangeRateByPair(@RequestParam final String from, final @RequestParam String to) {
        return exchangeRateService.getExchangeRateByPair(from, to);
    }

    @GetMapping("/all")
    public List<ExchangeRate> getAllExchangeRate() {
        return exchangeRateService.getAllExchangeRate();
    }

    @GetMapping("/{id}")
    public ExchangeRate getExchangeRateById(@PathVariable final Long id) {
        return exchangeRateService.getExchangeRateById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final Long id) {
      exchangeRateService.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllExchangeRate() {
        exchangeRateService.deleteAllExchangeRate();
    }
}
