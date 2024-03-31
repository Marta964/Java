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
    private float rate;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "exchangeRate")
    private List<Convert> convertations;

    public ExchangeRate(String data, float exchangeRate) {
        this.data = data;
        this.rate = exchangeRate;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public List<Convert> getConvertations() {
        return convertations;
    }

    public void setConvertations(List<Convert> convertations) {
        this.convertations = convertations;
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

}
