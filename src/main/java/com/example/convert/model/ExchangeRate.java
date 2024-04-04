package com.example.convert.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="exchangeRate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("time_last_update_utc")
    private String data;
    @JsonProperty("base_code")
    private String from;
    @JsonProperty("target_code")
    private String to;
    @JsonProperty("conversion_rate")
    private Float rate;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "rate")
    private List<Convert> convertations;

    public ExchangeRate(String data, String from, String to, Float rate) {
        this.data = data;
        this.from = from;
        this.to = to;
        this.rate = rate;
    }
    public ExchangeRate(){

    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public void setRate(){}
}
