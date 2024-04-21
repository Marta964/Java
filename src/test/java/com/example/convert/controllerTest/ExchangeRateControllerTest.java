package com.example.convert.controllerTest;

import com.example.convert.controller.ExchangeRateController;
import com.example.convert.model.ExchangeRate;
import com.example.convert.service.ExchangeRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExchangeRateControllerTest {
    @Mock
    private ExchangeRateService exchangeRateService;

    @InjectMocks
    private ExchangeRateController exchangeRateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateExchangeRate() {
        // Mock data
        ExchangeRate mockRate = new ExchangeRate();
        mockRate.setFrom("USD");
        mockRate.setTo("EUR");
        when(exchangeRateService.createExchangeRate("USD", "EUR")).thenReturn(mockRate);

        // Call the method
        ResponseEntity<ExchangeRate> result = exchangeRateController.createExchangeRate("USD", "EUR");

        // Verify results
        assertEquals(mockRate, result.getBody());
    }

    @Test
    void testGetAllExchangeRates() {
        List<ExchangeRate> mockRates = new ArrayList<>();
        mockRates.add(new ExchangeRate("data","USD", "EUR", 1.25f));
        mockRates.add(new ExchangeRate("data","EUR", "GBP", 0.85f));
        when(exchangeRateService.getAllExchangeRate()).thenReturn(mockRates);

        List<ExchangeRate> result = exchangeRateController.getAllExchangeRate();

        assertEquals(mockRates, result);
    }
    @Test
    void testGetExchangeRateByPair() {
        String from = "USD";
        String to = "EUR";
        List<ExchangeRate> mockRate = new ArrayList<>();
        when(exchangeRateService.getExchangeRateByPair(from,to)).thenReturn(mockRate);

        List<ExchangeRate> result = exchangeRateController.getExchangeRateByPair(from,to);

        assertEquals(mockRate, result);
    }
    @Test
    void testGetExchangeRateById(){
        Long id = 1L;
        ExchangeRate rate = new ExchangeRate();
        when(exchangeRateService.getExchangeRateById(id)).thenReturn(rate);

        ExchangeRate result = exchangeRateController.getExchangeRateById(id);

        assertEquals(rate,result);
    }

    @Test
    void create(){
        ExchangeRate rate =new ExchangeRate();
        when(exchangeRateService.create(rate)).thenReturn(rate);

        ExchangeRate result = exchangeRateController.create(rate);
        assertEquals(rate,result);
    }

    @Test
     void TestCreateBulkExchangeRates(){
        List<ExchangeRate> rates = new ArrayList<>();
        when(exchangeRateService.createBulkExchangeRates(rates)).thenReturn(rates);

        List<ExchangeRate> result = exchangeRateController.createBulkExchangeRates(rates);

        assertEquals(rates, result);
    }
    @Test
    void testUpdateExchangeRate() {
        ExchangeRate mockRate = new ExchangeRate("data","USD", "EUR", 1.25f);
        when(exchangeRateService.updateExchangeRate(1L, 1.3f)).thenReturn(mockRate);

        ExchangeRate result = exchangeRateController.updateExchangeRate(1L,  1.3f);

        assertEquals(mockRate.getRate(), result.getRate());
    }


}
