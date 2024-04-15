package com.example.convert.ServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.convert.cache.CacheManager;
import com.example.convert.model.ExchangeRate;
import com.example.convert.repository.ExchangeRateRepository;
import com.example.convert.service.ExchangeRateService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTest {
    @Mock
    private ExchangeRateRepository exchangeRateRepo;
    @Mock
    private CacheManager<String, Object> cache;
    @InjectMocks
    private ExchangeRateService exchangeRateService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetExchangeRateByPair() {
        List<ExchangeRate> testRates = new ArrayList<>();
        String from = "USD";
        String to = "EUR";
        testRates.add(new ExchangeRate("data",from,to,1.5f));


        when(exchangeRateRepo.getExchangeRateByPair(from, to)).thenReturn(testRates);

        List<ExchangeRate> result = exchangeRateService.getExchangeRateByPair(from,to);

        assertEquals(testRates, result);
        //verify(exchangeRateRepo,times(1)).
    }

    @Test
    void testCreateExchangeRate(){
        //ExchangeRate testRate = new ExchangeRate("data","from","to",0.0);
        String from = "USD";
        String to = "EUR";
        ExchangeRate testRate = new ExchangeRate();

        when(exchangeRateRepo.save(Mockito.any(ExchangeRate.class))).thenReturn(testRate);

        ExchangeRate result = exchangeRateService.createExchangeRate(from,to);

        assertEquals(testRate,result);
        verify(exchangeRateRepo,times(1)).getExchangeRateByPair(from,to);
    }

    @Test
    void testUpdateExchangeRate(){
        Long id = 1L;
        Float exchangeRateValue = 1.5f;
        ExchangeRate testRate = new ExchangeRate();

        when(exchangeRateRepo.findById(id)).thenReturn(Optional.of(testRate));

        ExchangeRate result = exchangeRateService.updateExchangeRate(id, exchangeRateValue);

        assertEquals(exchangeRateValue, result.getRate());
       // verify(exchangeRateRepo,times(1)).ge
    }

    @Test
    void testGetAllExchangeRate() {
        List<ExchangeRate> testRates = new ArrayList<>();
        testRates.add(new ExchangeRate("data1", "from1", "to1",0.0f));
        testRates.add(new ExchangeRate("data2", "from2", "to2", 0.0f));

        when(exchangeRateRepo.findAll()).thenReturn(testRates);

        List<ExchangeRate> result = exchangeRateService.getAllExchangeRate();

        assertEquals(testRates,result);

        // verify(exchangeRateRepo,times(1)).findAll();
    }

    @Test
    public void testGetExchangeRateById() {
        Long id = 1L;
        ExchangeRate exchangeRate = new ExchangeRate();

        when(exchangeRateRepo.findById(id)).thenReturn(Optional.of(exchangeRate));

        ExchangeRate result = exchangeRateService.getExchangeRateById(id);

        assertEquals(exchangeRate, result);
    }

    @Test
    void testDeleteById(){
        Long id = 1L;

        when(exchangeRateRepo.existsById(id)).thenReturn(true);

        exchangeRateService.deleteById(id);

        verify(exchangeRateRepo,times(1)).deleteById(id);
        verify(cache,times(1)).clear();
    }

    @Test
    public void testDeleteAllExchangeRate() {
        exchangeRateService.deleteAllExchangeRate();

        Mockito.verify(exchangeRateRepo, Mockito.times(1)).deleteAll();
        Mockito.verify(cache, Mockito.times(1)).clear();
    }
}