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
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService=exchangeRateService;
    }

    @PostMapping()
    public ExchangeRate createExchangeRate(@RequestParam String from, @RequestParam String to){
        return exchangeRateService.createExchangeRate(from,to);
    }

    @PutMapping("/{id}")
    public ExchangeRate updateExchangeRate(@PathVariable Long id,@RequestParam Float exchangeRate){
        return exchangeRateService.updateExchangeRate(id,exchangeRate);
    }

    @GetMapping("/all")
    public List<ExchangeRate> getAllExchangeRate(){
        return exchangeRateService.getAllExchangeRate();
    }

    @GetMapping("/{id}")
    public ExchangeRate getExchangeRateById(@PathVariable Long id){
        return exchangeRateService.getExchangeRateById(id);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
      exchangeRateService.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllExchangeRate(){
        exchangeRateService.deleteAllExchangeRate();
    }
}
