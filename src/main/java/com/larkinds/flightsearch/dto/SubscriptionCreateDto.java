package com.larkinds.flightsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
@Data
public class SubscriptionCreateDto {
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("ChatId")
    private long chatId;
    @JsonProperty("Currency")
    private String currency;
    @JsonProperty("Locale")
    private String locale;
    @JsonProperty("OriginPlace")
    private String originPlace;
    @JsonProperty("DestinationPlace")
    private String destinationPlace;
    @JsonProperty("OutboundPartialDate")
    private LocalDate outboundPartialDate;
    @JsonProperty("InboundPartialDate")
    private LocalDate inboundPartialDate;
}
