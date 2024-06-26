package com.example.convert.controller;

import com.example.convert.model.Convert;
import com.example.convert.service.ConvertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Conversions", description = "Managing conversions.")
@RestController
@RequestMapping("/convert")
public class ConvertController {

    private final ConvertService service;

    @Autowired
    public ConvertController(final ConvertService service) {
        this.service = service;
    }


    @PutMapping("/{id}")
    public Convert updateConversion(@PathVariable final Long id,
                                    @RequestParam final Float amount) {
        return service.updateConversion(id, amount);
    }
    @GetMapping("/all")
    public List<Convert> getAllConvertations() {
        return service.getAllConversions();
    }
    @GetMapping("/{id}")
    public Convert getConversionById(@PathVariable final Long id) {
        return service.getConversionById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllConversions() {
        service.deleteAllConversions();
    }

    @DeleteMapping("/{id}")
    public void deleteConversionById(@PathVariable final Long id) {
        service.deleteConversionById(id);
    }
    @PostMapping("/{id}")
    public Convert createConversation(@PathVariable final Long id,
                                      @RequestParam final Float amountFrom) {
        return service.createConversation(id, amountFrom);
    }
}