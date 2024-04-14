package com.example.convert.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a convert entity.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "convert")
public class Convert {
    /**
     * The unique identifier of the conversion.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The amount in the base currency.
     */
    private Float amountFrom;
    /**
     * The amount in the quoted currency.
     */
    private Float amountTo;
    /**
     * The exchange rate to which they convert.
     */
    @ManyToOne
    @JoinColumn(name = "exchangeRateId")
    @JsonIgnoreProperties("conversions")
    private ExchangeRate rate;
}

