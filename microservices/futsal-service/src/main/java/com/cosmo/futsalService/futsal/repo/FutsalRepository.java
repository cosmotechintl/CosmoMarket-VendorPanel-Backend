package com.cosmo.futsalService.futsal.repo;

import com.cosmo.futsalService.futsal.entity.Futsal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FutsalRepository extends JpaRepository<Futsal, Long> {
    Optional<Futsal> findByVendorCode(String code);
}
