package com.larkinds.flightsearch.service.localisation.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larkinds.flightsearch.dto.CountryDto;
import com.larkinds.flightsearch.dto.CurrencyDto;
import com.larkinds.flightsearch.service.localisation.LocalisationClient;
import com.larkinds.flightsearch.service.rest.UniRestService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpStatus;
import org.apache.tomcat.jni.Local;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.larkinds.flightsearch.service.rest.impl.UniRestServiceImpl.*;

@Service
public class LocalisationClientImpl implements LocalisationClient {
    private UniRestService uniRestService;
    private ObjectMapper objectMapper;

    @Autowired
    LocalisationClientImpl(UniRestService uniRestService, ObjectMapper objectMapper) {
        this.uniRestService = uniRestService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<CountryDto> retrieveCoutries(String locale) throws IOException, JSONException {
        HttpResponse<JsonNode> response = uniRestService.get(String.format(COUNTRIES_FORMAT, locale));
        if (response.getCode() != HttpStatus.SC_OK) {
            return null;
        }
        String jsonList = response.getBody().getObject().get(COUNTRIES_KEY).toString();
        return objectMapper.readValue(jsonList, new TypeReference<List<CountryDto>>() {
        });
    }

    @Override
    public List<CurrencyDto> retrieveCurrencies() throws IOException, JSONException, UnirestException{
        HttpResponse<JsonNode> response = uniRestService.get(CURRENCIES_FORMAT);
        if (response.getCode() != HttpStatus.SC_OK) {
            return  null;
        }
        String jsonList = response.getBody().getObject().get(CURRENCIES_KEY).toString();
        return objectMapper.readValue(jsonList, new TypeReference<List<CurrencyDto>>() {
        });
    }
}
