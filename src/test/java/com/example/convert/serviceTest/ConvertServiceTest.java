package com.example.convert.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.convert.model.Convert;
import com.example.convert.model.ExchangeRate;
import com.example.convert.repository.ConvertRepository;
import com.example.convert.repository.ExchangeRateRepository;
import com.example.convert.service.ConvertService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@ExtendWith(MockitoExtension.class)
class ConvertServiceTest {
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
    void testCreateConversion() {
        Long id = 1L;
        Float amountFrom = 100f;
        ExchangeRate rate = new ExchangeRate();
        rate.setRate(1.5f);

        Convert testConvert = new Convert();
        testConvert.setAmountFrom(amountFrom);
        testConvert.setAmountTo(amountFrom * rate.getRate());
        testConvert.setRate(rate);

        when(exchangeRateRepo.findById(id)).thenReturn(Optional.of(rate));
        when(convertRepo.save(testConvert)).thenReturn(testConvert);

        Convert result = convertService.createConversation(id, amountFrom);

        assertEquals(amountFrom, result.getAmountFrom());
        assertEquals(testConvert.getAmountTo(),result.getAmountTo());
        verify(exchangeRateRepo,times(1)).findById(id);
        verify(convertRepo,times(0)).save(testConvert);
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
        verify(convertRepo,times(1)).findById(id);
    }

    @Test
    void testGetAllConversations() {
        List<Convert> testConverts = new ArrayList<>();
        when(convertRepo.findAll()).thenReturn(testConverts);

        List<Convert> result = convertService.getAllConversions();

        assertEquals(testConverts, result);
        verify(convertRepo,times(1)).findAll();
    }

    @Test
    void testGetConversionById() {
        Long id = 1L;
        Convert testConvert = new Convert();
        testConvert.setId(id);

        when(convertRepo.findById(id)).thenReturn(Optional.of(testConvert));

        Convert result = convertService.getConversionById(id);

        assertEquals(testConvert, result);
        verify(convertRepo,times(1)).findById(anyLong());
    }

    @Test
    void testDeleteAllConversions() {
        convertService.deleteAllConversions();
        verify(convertRepo, times(1)).deleteAll();
    }

    @Test
    void testDeleteConversionById() {
        Long id = 1L;

        when(convertRepo.existsById(id)).thenReturn(true);

        convertService.deleteConversionById(id);

        verify(convertRepo, times(1)).deleteById(id);
    }

}
