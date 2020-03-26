package com.larkinds.flightsearch.service.flightprices;

import com.larkinds.flightsearch.dto.FlightPricesDto;
import com.larkinds.flightsearch.model.Subscription;
import org.json.JSONException;

public interface FlightPriceService {
    Integer findMinPrice(FlightPricesDto flightPricesDto);
    FlightPricesDto findFlightPrice(Subscription subscription) throws JSONException;
}
