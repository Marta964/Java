package com.example.convert.controllerTest;

import com.example.convert.controller.ConvertController;
import com.example.convert.model.Convert;
import com.example.convert.service.ConvertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertControllerTest {
    @Mock
    private ConvertService convertService;

    @InjectMocks
    private ConvertController convertController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateConversionTest() {
        Convert test = new Convert();
        when(convertService.updateConversion(1L,1.3f)).thenReturn(test);

        Convert result = convertController.updateConversion(1L,1.3f);

        assertEquals(test.getRate(),result.getRate());
    }
    @Test
    void getAllConversionsTest() {
        List<Convert> test = new ArrayList<>();
        when(convertService.getAllConversions()).thenReturn(test);

        List<Convert> result = convertController.getAllConvertations();
        assertEquals(test,result);
    }
    @Test
    void getConversionByIdTest() {
        Long id = 1L;
        Convert test = new Convert();
        when(convertService.getConversionById(id)).thenReturn(test);

        Convert result = convertController.getConversionById(id);

        assertEquals(test,result);
    }
    @Test
    void createConversationTest() {
        Convert test = new Convert();
        when(convertService.createConversation(1L,1.5f)).thenReturn(test);

        Convert result = convertController.createConversation(1L,1.5f);

        assertEquals(test,result);
    }

}
