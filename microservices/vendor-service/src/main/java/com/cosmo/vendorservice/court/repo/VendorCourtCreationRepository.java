package com.cosmo.vendorservice.court.repo;

import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.vendorservice.court.entity.CourtDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface VendorCourtCreationRepository extends JpaRepository<CourtDetails, Long> {
    Optional<CourtDetails> findByName(String Name);
}
