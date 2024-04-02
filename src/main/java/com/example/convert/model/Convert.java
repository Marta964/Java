package com.example.convert.model;


import jakarta.persistence.*;


@Entity
@Table(name = "convert")
public class Convert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float amountFrom;
    private float amountTo;
    @ManyToOne
    @JoinColumn(name = "exchangeRateId")
    private ExchangeRate exchangeRate;


    public Convert(float amountFrom, float amountTo) {
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(float amountFrom) {
        this.amountFrom = amountFrom;
    }

    public float getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(float amountTo) {
        this.amountTo = amountTo;
    }
}

