package com.example.convert.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversionResponse {
    /**
     *
     */
    @JsonProperty("time_last_update_utc")
    private String data;
    @JsonProperty("base_code")
    private String baseCode;
    @JsonProperty("target_code")
    private String targetCode;
    @JsonProperty("conversion_rate")
    private Float conversionRate;
    @JsonProperty("conversion_result")
    private Float conversionResult;
}

