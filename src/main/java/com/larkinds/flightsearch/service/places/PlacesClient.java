package com.larkinds.flightsearch.service.places;

import com.larkinds.flightsearch.dto.PlaceDto;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface PlacesClient {
    List<PlaceDto> retrievePlacesList(String query, String country, String currency, String locale)
            throws IOException, UnirestException, JSONException;
}
