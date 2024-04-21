package com.example.convert.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.convert.cache.CacheManager;
import com.example.convert.model.ExchangeRate;
import com.example.convert.repository.ExchangeRateRepository;
import com.example.convert.service.ExchangeRateService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.*;

class ExchangeRateServiceTest {
    @Mock
    private ExchangeRateRepository exchangeRateRepo;
    @Mock
    private CacheManager<String, Object> cache;
    @Mock
    ExchangeRate exchangeRate;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetExchangeRateByPair() {
        List<ExchangeRate> testRates = new ArrayList<>();
        String from = "USD";
        String to = "EUR";
        testRates.add(new ExchangeRate("data",from,to,1.5f));


        when(exchangeRateRepo.getExchangeRateByPair(from, to)).thenReturn(testRates);

        List<ExchangeRate> result = exchangeRateService.getExchangeRateByPair(from,to);

        assertEquals(testRates, result);
        verify(exchangeRateRepo,times(1)).getExchangeRateByPair(from,to);
    }

    @Test
    void TestCreate(){
        ExchangeRate testRate = new ExchangeRate();
        testRate.setTo("to");

        when(exchangeRateRepo.save(testRate)).thenReturn(testRate);

        exchangeRateService.create(testRate);

        verify(exchangeRateRepo,times(1)).save(testRate);
    }
    /*@Test
    void createExchangeRateTest() {
        ExchangeRate testRate = new ExchangeRate();
        when(exchangeRateRepo.save(testRate)).thenReturn(testRate);

        ExchangeRate result = exchangeRateService.createExchangeRate("USD","BYN");

        assertEquals(testRate,result);
    }*/
    @Test
    void TestCreateBulkExchangeRates(){
        List<ExchangeRate> rates = new ArrayList<>();
        ExchangeRate testRate = new ExchangeRate();
        testRate.setFrom("USD");
        testRate.setTo("BYN");
        testRate.setId(1L);
        rates.add(testRate);

        when(exchangeRateRepo.existsByRate(anyFloat())).thenReturn(false);
        when(exchangeRateRepo.existsByFrom(anyString())).thenReturn(false);
        when(exchangeRateRepo.existsByTo(anyString())).thenReturn(false);
        when(exchangeRateRepo.save(any(ExchangeRate.class))).thenReturn(testRate);

        List<ExchangeRate> result = exchangeRateService.createBulkExchangeRates(rates);

        assertEquals(rates,result);
        verify(exchangeRateRepo,times(1)).save(any(ExchangeRate.class));
    }

    @Test
    void testUpdateExchangeRate(){
        Long id = 1L;
        Float exchangeRateValue = 1.5f;
        ExchangeRate testRate = new ExchangeRate();
        testRate.setId(id);
        testRate.setRate(exchangeRateValue);

        when(exchangeRateRepo.findById(id)).thenReturn(Optional.of(testRate));

        ExchangeRate result = exchangeRateService.updateExchangeRate(id, exchangeRateValue);

        assertEquals(exchangeRateValue, result.getRate());
        verify(exchangeRateRepo,times(1)).findById(id);
        verify(exchangeRateRepo,times(0)).save(any(ExchangeRate.class));
       // verify(cache,times(1)).put("rate 1",testRate);
    }

    @Test
    void testGetAllExchangeRate() {
        List<ExchangeRate> testRates = new ArrayList<>();

        when(exchangeRateRepo.findAll()).thenReturn(testRates);

        List<ExchangeRate> result = exchangeRateService.getAllExchangeRate();

        assertEquals(testRates,result);

        verify(exchangeRateRepo,times(1)).findAll();
    }

    @Test
    void testGetExchangeRateById() {
        Long id = 1L;
        ExchangeRate exchangeRate = new ExchangeRate();

        when(exchangeRateRepo.findById(id)).thenReturn(Optional.of(exchangeRate));

        ExchangeRate result = exchangeRateService.getExchangeRateById(id);

        assertEquals(exchangeRate, result);
        verify(exchangeRateRepo,times(1)).findById(anyLong());
    }

    @Test
    void testDeleteById(){
        Long id = 1L;

        when(exchangeRateRepo.existsById(id)).thenReturn(true);

        exchangeRateService.deleteById(id);

        verify(exchangeRateRepo,times(1)).existsById(id);
        verify(exchangeRateRepo,times(1)).deleteById(id);
        //verify(cache,times(1)).remove("rate"+id.toString());
    }

    @Test
    void testDeleteAllExchangeRate() {
        exchangeRateService.deleteAllExchangeRate();

        verify(exchangeRateRepo,times(1)).deleteAll();
        verify(cache, times(1)).clear();
    }
}