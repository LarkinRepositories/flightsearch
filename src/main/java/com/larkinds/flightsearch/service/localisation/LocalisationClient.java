package com.larkinds.flightsearch.service.localisation;

import com.larkinds.flightsearch.dto.CountryDto;
import com.larkinds.flightsearch.dto.CurrencyDto;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface LocalisationClient {
    List<CountryDto> retrieveCoutries(String locale) throws IOException, JSONException;
    List<CurrencyDto> retrieveCurrencies() throws IOException, UnirestException, JSONException;
}
