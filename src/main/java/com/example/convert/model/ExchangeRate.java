package com.example.convert.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnoreProperties("rate")
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "rate")
    private List<Convert> conversions;
}
