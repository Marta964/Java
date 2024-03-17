package com.example.convert.controller;

import com.example.convert.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/convert")
public class ConvertController {

    private final ConvertService service;
    @Autowired
    public ConvertController(ConvertService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        return new ResponseEntity<>(service.convertCurrency(from, to, amount), HttpStatus.OK);
    }

}