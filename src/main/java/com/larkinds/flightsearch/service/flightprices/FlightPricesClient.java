package com.larkinds.flightsearch.service.flightprices;

import com.larkinds.flightsearch.dto.FlightPricesDto;
import org.json.JSONException;

public interface FlightPricesClient {
    FlightPricesDto browseQuotes(String country, String currency, String locale,
                                 String originPlace, String destinationPlace, String outboundPartialDate) throws JSONException;

    FlightPricesDto browseQuotes(String country, String currency, String locale, String originPlace,
                                 String destinationPlace, String outboundPartialDate, String inboundPartialDate);
}
