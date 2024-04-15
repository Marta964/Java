package com.example.convert.ServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.convert.model.ConversionResponse;
import com.example.convert.model.Convert;
import com.example.convert.model.ExchangeRate;
import com.example.convert.repository.ConvertRepository;
import com.example.convert.repository.ExchangeRateRepository;
import com.example.convert.service.ConvertService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ConvertServiceTest {
    @Mock
    private ConvertRepository convertRepo;
    @Mock
    private ExchangeRateRepository exchangeRateRepo;
    @InjectMocks
    private ConvertService convertService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertCurrency() {
        RestTemplate restTemplate = new RestTemplate();
        String from = "USD";
        String to = "EUR";
        Float amount = 100f;
        ConversionResponse expectedResponse = new ConversionResponse();
        String apiKey = "your_api_key_here";

        String apiUrl = "https://v6.exchangerate-api.com/v6";
        String url = String.format("%s/%s/pair/%s/%s/%s", apiUrl, apiKey, from, to, amount);

        when(restTemplate.getForObject(url, ConversionResponse.class, from, to, amount)).thenReturn(expectedResponse);

        ConversionResponse actualResponse = convertService.convertCurrency(from, to, amount);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testCreateConversion() {
        Long id = 1L;
        Float amountFrom = 100f;
        ExchangeRate rate = new ExchangeRate();
        rate.setRate(1.5f);

        Convert convert = new Convert();
        convert.setAmountFrom(amountFrom);
        convert.setAmountTo(amountFrom * rate.getRate());
        convert.setRate(rate);

        when(exchangeRateRepo.findById(id)).thenReturn(Optional.of(rate));
        when(convertRepo.save(convert)).thenReturn(convert);

        Convert actualConvert = convertService.createConversation(id, amountFrom);

        assertEquals(convert, actualConvert);
    }

    @Test
    void testUpdateConversion() {
        Long id = 1L;
        Float amount = 200f;
        ExchangeRate rate = new ExchangeRate();
        rate.setRate(1.5f);

        Convert convert = new Convert();
        convert.setId(id);
        convert.setAmountFrom(100f);
        convert.setAmountTo(150f);
        convert.setRate(rate);

        when(convertRepo.findById(id)).thenReturn(Optional.of(convert));

        Convert updatedConvert = convertService.updateConversion(id, amount);

        assertEquals(convert, updatedConvert);
        assertEquals(amount, updatedConvert.getAmountFrom());
        assertEquals(amount * rate.getRate(), updatedConvert.getAmountTo());
    }

    @Test
    void testGetAllConversations() {
        List<Convert> expectedConverts = new ArrayList<>();
        when(convertRepo.findAll()).thenReturn(expectedConverts);

        List<Convert> actualConverts = convertService.getAllConvertations();

        assertEquals(expectedConverts, actualConverts);
    }

    @Test
    void testGetConversionById() {
        Long id = 1L;
        Convert expectedConvert = new Convert();
        expectedConvert.setId(id);

        when(convertRepo.findById(id)).thenReturn(Optional.of(expectedConvert));

        Convert actualConvert = convertService.getConversionById(id);

        assertEquals(expectedConvert, actualConvert);
    }

    @Test
    void testDeleteAllConversions() {
        convertService.deleteAllConversions();
        verify(convertRepo, times(1)).deleteAll();
    }

    @Test
    void testDeleteConversionById() {
        Long id = 1L;
        convertService.deleteConversionById(id);
        verify(convertRepo, times(1)).deleteById(id);
    }
}
