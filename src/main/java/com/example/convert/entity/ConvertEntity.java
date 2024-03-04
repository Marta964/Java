package com.example.convert.entity;


import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.*;


//@Data
//@Builder
public class ConvertEntity {
    private String from;
    private String to;
    private double amountFrom;
    private double amountTo;
}
