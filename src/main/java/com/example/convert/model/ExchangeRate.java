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
    private String From;
    @JsonProperty("target_code")
    private String To;
    @JsonProperty("conversion_rate")
    private float rate;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "exchangeRate")
    private List<Convert> convertations;

    public ExchangeRate(String data, String from, String to, float rate) {
        this.data = data;
        From = from;
        To = to;
        this.rate = rate;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
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
