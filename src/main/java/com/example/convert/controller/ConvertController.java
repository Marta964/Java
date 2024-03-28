package com.example.convert.controller;

import com.example.convert.model.Convert;
import com.example.convert.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.convert.model.ConvertionRersponse;

@RestController
@RequestMapping("/convert")
public class ConvertController {

    private final ConvertService service;

    @Autowired
    public ConvertController(ConvertService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        ConvertionRersponse a = service.convertCurrency(from,to,amount);
        return ResponseEntity.ok(a);
    }


    @PostMapping
    public ResponseEntity<Object> convertation(@RequestBody Convert convertation){
        try{
            service.convertation(convertation);
            return ResponseEntity.ok("Work");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteConvertation(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}