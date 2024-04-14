package com.example.convert.controller;

import com.example.convert.model.ExchangeRate;
import com.example.convert.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchangerate")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(final ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @PostMapping()
    public ExchangeRate createExchangeRate(@RequestParam final String from, @RequestParam final String to) {
        return exchangeRateService.createExchangeRate(from, to);
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
