package com.larkinds.flightsearch.service.flightprices.impl;

import com.larkinds.flightsearch.dto.FlightPricesDto;
import com.larkinds.flightsearch.model.Subscription;
import com.larkinds.flightsearch.service.flightprices.FlightPriceService;
import com.larkinds.flightsearch.service.flightprices.FlightPricesClient;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightPriceServiceImpl implements FlightPriceService {
    @Autowired
    FlightPricesClient flightPricesClient;

    @Override
    public Integer findMinPrice(FlightPricesDto flightPricesDto) {
        return flightPricesDto.getQuotas().get(0).getMinPrice();
    }

    @Override
    public FlightPricesDto findFlightPrice(Subscription subscription) throws JSONException {
        if (subscription.getInboundPartialDate() == null) {
            return flightPricesClient.browseQuotes(subscription.getCountry(), subscription.getCurrency(), subscription.getLocale(),
                    subscription.getOriginPlace(), subscription.getDestinationPlace(),
                    subscription.getOutboundPartialDate().toString());
        } else {
                return flightPricesClient
                        .browseQuotes(subscription.getCountry(), subscription.getCurrency(), subscription.getLocale(),
                                subscription.getOriginPlace(), subscription.getDestinationPlace(),
                                subscription.getOutboundPartialDate().toString(), subscription.getInboundPartialDate().toString());
        }
    }
}
