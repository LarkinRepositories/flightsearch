package com.larkinds.flightsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarrierDto {
    @JsonProperty("CarrierId")
    private int carrierId;
    @JsonProperty("Name")
    private String name;
}
