package com.bancow.process.domain.farm.repository;

import com.bancow.process.domain.farm.domain.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmRepository extends JpaRepository<Farm, Long> {
    Optional<Farm> findByPhoneNumber(String phoneNumber);

    List<Farm> findFarmsByInvestigationRequestIsNotNull();
}
