package com.larkinds.flightsearch.repository;

import com.larkinds.flightsearch.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubsriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findAllByEmail(String email);
}
