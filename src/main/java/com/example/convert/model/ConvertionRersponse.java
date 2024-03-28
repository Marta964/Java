package com.example.convert.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConvertionRersponse {

    @JsonProperty
    private String base_code;
    private String target_code;
    private float conversion_rate;
    private float conversion_result;

    public String getBaseCode() {
        return base_code;
    }

    public void setBaseCode(String base_code) {
        this.base_code = base_code;
    }

    public String getTargetCode() {
        return target_code;
    }

    public void setTargetCode(String target_code) {
        this.target_code = target_code;
    }

    public float getConversionRate() {
        return conversion_rate;
    }

    public void setConversionRate(float conversion_rate) {
        this.conversion_rate = conversion_rate;
    }

    public float getConversionResult() {
        return conversion_result;
    }

    public void setConversionResult(float conversion_result) {
        this.conversion_result = conversion_result;
    }
}

