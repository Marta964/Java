package com.example.convert.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConvertionRersponse {

    @JsonProperty
    private String base_code;
    private String target_code;
    private float conversion_rate;
    private float conversion_result;

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public String getTarget_code() {
        return target_code;
    }

    public void setTarget_code(String target_code) {
        this.target_code = target_code;
    }

    public float getConversion_rate() {
        return conversion_rate;
    }

    public void setConversion_rate(float conversion_rate) {
        this.conversion_rate = conversion_rate;
    }

    public float getConversion_result() {
        return conversion_result;
    }

    public void setConversion_result(float conversion_result) {
        this.conversion_result = conversion_result;
    }
}

