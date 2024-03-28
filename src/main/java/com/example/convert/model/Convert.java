package com.example.convert.model;


import jakarta.persistence.*;


@Entity
@Table(name = "convert")
public class Convert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amountFrom;
    private double amountTo;
    @ManyToOne
    @JoinColumn(name = "exchangeRateId")
    private ExchangeRate exchangeRate;


    public Convert(double amountFrom, double amountTo) {
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(double amountFrom) {
        this.amountFrom = amountFrom;
    }

    public double getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(double amountTo) {
        this.amountTo = amountTo;
    }
}

