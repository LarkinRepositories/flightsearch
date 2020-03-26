package com.larkinds.flightsearch.service.subscription;

import com.larkinds.flightsearch.dto.SubscriptionCreateDto;
import com.larkinds.flightsearch.dto.SubscriptionDto;
import com.larkinds.flightsearch.dto.SubscriptionUpdateDto;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDto create(SubscriptionCreateDto subScriptionDto);
    List<SubscriptionDto> findByEmail(String email);
    SubscriptionDto update(SubscriptionUpdateDto subScriptionDto);
    void delete(Long id);

}
