package com.cosmo.futsalService.court.repo;

import com.cosmo.futsalService.court.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CourtRepository extends JpaRepository<Court, Long> {
    Optional<Court> findByName(String Name);
}
