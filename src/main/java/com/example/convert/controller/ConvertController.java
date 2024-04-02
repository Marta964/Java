package com.example.convert.controller;

import com.example.convert.model.Convert;
import com.example.convert.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.convert.model.ConvertionResponse;

import java.util.List;

@RestController
@RequestMapping("/convert")
public class ConvertController {

    private final ConvertService service;

    @Autowired
    public ConvertController(ConvertService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<Object> convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam float amount) {
        ConvertionResponse a = service.convertCurrency(from,to,amount);
        return ResponseEntity.ok(a);
    }





    @GetMapping("/all")
    public List<Convert> getAllConversions(){
        return service.getAllConverions();
    }


    @GetMapping("/{id}")
    public Convert getConversionById(@PathVariable Long id){
        return service.getConversionById(id);
    }


    @DeleteMapping("/all")
    public void deleteAllConversions(){
        service.deleteAllConversions();
    }
    @DeleteMapping("/{id}")
    public Long deleteConversionById(@PathVariable Long id){
        return service.deleteConversionById(id);
    }
}