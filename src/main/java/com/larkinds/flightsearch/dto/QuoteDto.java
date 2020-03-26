package com.larkinds.flightsearch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuoteDto {
    @JsonProperty("QuoteId")
    private int id;
    @JsonProperty("MinPrice")
    private int minPrice;
    @JsonProperty("Direct")
    private boolean direct;
    @JsonProperty("DestinationId")
    private int destinationId;
    @JsonProperty("DepartureDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String departureDate;


}
