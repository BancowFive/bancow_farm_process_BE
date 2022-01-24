package com.bancow.process.repository;

import com.bancow.process.domain.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface FarmRepository extends JpaRepository<Farm,Long> {
    Optional<Farm> findByUserName(String userName);
}
