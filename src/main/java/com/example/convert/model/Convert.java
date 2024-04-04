package com.example.convert.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "convert")
public class Convert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amountFrom;
    private Float amountTo;
    @ManyToOne
    @JoinColumn(name = "exchangeRateId")
    private ExchangeRate rate;

    public Convert(Float amountFrom,Float amountTo, ExchangeRate exchangeRate) {
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
        this.rate = exchangeRate;
    }
    public Convert(){}
    public ExchangeRate getRate() {
        return rate;
    }

    public void setRate(ExchangeRate rate) {
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(Float amountFrom) {
        this.amountFrom = amountFrom;
    }

    public Float getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(Float amountTo) {
        this.amountTo = amountTo;
    }
}

