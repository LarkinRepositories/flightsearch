package com.larkinds.flightsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FlightPricesDto {
    @JsonProperty("Quotas")
    private List<QuoteDto> quotas;
    @JsonProperty("Carriers")
    private List<CarrierDto> carriers;
    @JsonProperty("Currencies")
    private List<CurrencyDto> currencies;
    @JsonProperty("Places")
    private List<PlaceDto> places;
}
