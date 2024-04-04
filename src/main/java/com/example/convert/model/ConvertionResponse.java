package com.example.convert.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConvertionResponse {
    @JsonProperty("time_last_update_utc")
    private String time;
    @JsonProperty("base_code")
    private String baseCode;
    @JsonProperty("target_code")
    private String targetCode;
    @JsonProperty("conversion_rate")
    private Float conversionRate;
    @JsonProperty("conversion_result")
    private Float conversionResult;

    public ConvertionResponse(String time, String baseCode, String targetCode, Float conversionRate, Float conversionResult) {
        this.time = time;
        this.baseCode = baseCode;
        this.targetCode = targetCode;
        this.conversionRate = conversionRate;
        this.conversionResult = conversionResult;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    public Float getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Float conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Float getConversionResult() {
        return conversionResult;
    }

    public void setConversionResult(Float conversionResult) {
        this.conversionResult = conversionResult;
    }
}

