package com.example.convert.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConvertionRersponse {


    @JsonProperty("base_code")
    private String baseCode;
    @JsonProperty("target_code")
    private String targetCode;
    @JsonProperty("conversion_rate")
    private float conversionRate;
    @JsonProperty("conversion_result")
    private float conversionResult;

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public float getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(float conversionRate) {
        this.conversionRate = conversionRate;
    }

    public float getConversionResult() {
        return conversionResult;
    }

    public void setConversionResult(float conversionResult) {
        this.conversionResult = conversionResult;
    }
}

