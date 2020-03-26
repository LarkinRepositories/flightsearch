package com.larkinds.flightsearch.email;

import com.larkinds.flightsearch.model.Subscription;

public interface EmailNotifierService {
    void notifySubscriber(Subscription subscription, Integer oldMinPrice, Integer newMinPrice);
    void notifyAddingSubscription(Subscription subscription);
}
