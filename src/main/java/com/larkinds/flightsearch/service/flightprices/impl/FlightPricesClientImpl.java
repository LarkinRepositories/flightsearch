package com.larkinds.flightsearch.service.flightprices.impl;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.larkinds.flightsearch.dto.*;
import com.larkinds.flightsearch.exceptions.FlightClientException;
import com.larkinds.flightsearch.service.flightprices.FlightPricesClient;
import com.larkinds.flightsearch.service.rest.UniRestService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.larkinds.flightsearch.service.rest.impl.UniRestServiceImpl.CURRENCIES_KEY;
import static com.larkinds.flightsearch.service.rest.impl.UniRestServiceImpl.PLACES_KEY;

@Service
public class FlightPricesClientImpl implements FlightPricesClient {
    private UniRestService uniRestService;
    private ObjectMapper objectMapper;

    public static final String BROWSE_QUOTES_FORMAT = "/apiservices/browsequotes/v1.0/%s/%s/%s/%s/%s/%s";
    public static final String OPTIONAL_BROWSE_QUOTES_FORMAT = BROWSE_QUOTES_FORMAT + "?inboundpartialdate=%s";

    public static final String QUOTES_KEY = "Quotes";
    public static final String ROUTES_KEY = "Routes";
    public static final String DATES_KEY = "Dates";
    public static final String CARRIERS_KEY = "Carriers";
    public static final String VALIDATIONS_KEY = "ValidationErrors";

    @Autowired
    public FlightPricesClientImpl(UniRestService uniRestService, ObjectMapper objectMapper) {
        this.uniRestService = uniRestService;
        this.objectMapper = objectMapper;
    }

    @Override
    public FlightPricesDto browseQuotes(String country, String currency, String locale, String originPlace, String destinationPlace, String outboundPartialDate) throws JSONException {
        HttpResponse<JsonNode> response = uniRestService.get(String
                .format(BROWSE_QUOTES_FORMAT, country, currency, locale, originPlace, destinationPlace,
                        outboundPartialDate));
        return mapToObject(response);
    }

    @Override
    public FlightPricesDto browseQuotes(String country, String currency, String locale, String originPlace, String destinationPlace, String outboundPartialDate, String inboundPartialDate) {
        return null;
    }

    private FlightPricesDto mapToObject(HttpResponse<JsonNode> response) throws JSONException {
        if (response.getCode() == HttpStatus.SC_OK) {
            FlightPricesDto flightPricesDto = new FlightPricesDto();
            flightPricesDto.setQuotas(readValue(response.getBody().getObject().get(QUOTES_KEY).toString(),
                    new TypeReference<List<QuoteDto>>() {
                    }));
            flightPricesDto.setCarriers(readValue(response.getBody().getObject().get(CARRIERS_KEY).toString(),
                    new TypeReference<List<CarrierDto>>() {
                    }));
            flightPricesDto.setCurrencies(readValue(response.getBody().getObject().get(CURRENCIES_KEY).toString(),
                    new TypeReference<List<CurrencyDto>>() {
                    }));
            flightPricesDto.setPlaces(readValue(response.getBody().getObject().get(PLACES_KEY).toString(),
                    new TypeReference<List<PlaceDto>>() {
                    }));
            return flightPricesDto;
        }
        throw new FlightClientException(String.format("There are validation errors. statusCode = %s", response.getCode()),
                readValue(response.getBody().getObject().get(VALIDATIONS_KEY).toString(),
                        new TypeReference<List<ValidationErrorDto>>() {
                        }));
    }

    private <T> List<T> readValue(String resultAsString, TypeReference<List<T>> valueTypeRef) {
        List<T> list;
        try {
            list = objectMapper.readValue(resultAsString, valueTypeRef);
        } catch (IOException e) {
            throw new FlightClientException("Object Mapping failure.", e);
        }
        return list;
    }
}
