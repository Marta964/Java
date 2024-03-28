package com.example.convert.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="exchangeRate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private String currencies;
    private float exchangeRate;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "exchangeRate")
    private List<Convert> convertations;

    public ExchangeRate(String data, float exchangeRate) {
        this.data = data;
        this.exchangeRate = exchangeRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}
