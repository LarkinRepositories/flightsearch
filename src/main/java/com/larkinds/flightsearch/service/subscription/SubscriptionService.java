package com.larkinds.flightsearch.service.subscription;

import com.larkinds.flightsearch.dto.SubScriptionCreateDto;
import com.larkinds.flightsearch.dto.SubScriptionDto;
import com.larkinds.flightsearch.dto.SubScriptionUpdateDto;

import java.util.List;

public interface SubscriptionService {
    SubScriptionDto create(SubScriptionCreateDto subScriptionDto);
    List<SubScriptionDto> findByEmail(String email);
    SubScriptionDto update(SubScriptionUpdateDto subScriptionDto);
    void delete(Long id);

}
