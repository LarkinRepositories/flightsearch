package com.larkinds.flightsearch.service.places.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larkinds.flightsearch.dto.PlaceDto;
import com.larkinds.flightsearch.service.places.PlacesClient;
import com.larkinds.flightsearch.service.rest.UniRestService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.larkinds.flightsearch.service.rest.impl.UniRestServiceImpl.PLACES_FORMAT;
import static com.larkinds.flightsearch.service.rest.impl.UniRestServiceImpl.PLACES_KEY;

@Service
public class PlacesClientImpl implements PlacesClient {
    private UniRestService uniRestService;
    private ObjectMapper objectMapper;

    @Autowired
    public PlacesClientImpl(UniRestService uniRestService, ObjectMapper objectMapper) {
        this.uniRestService = uniRestService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<PlaceDto> retrievePlacesList(String query, String country, String currency, String locale)
            throws IOException, UnirestException, JSONException {
        HttpResponse<JsonNode> response = uniRestService
                .get(String.format(PLACES_FORMAT,country, currency, locale, query));
        if (response.getCode() != HttpStatus.SC_OK) return null;
        String jsonList = response.getBody().getObject().get(PLACES_KEY).toString();
        return objectMapper.readValue(jsonList, new TypeReference<List<PlaceDto>>() {
        });
    }


}
