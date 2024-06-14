package com.cosmo.vendorservice.businesshour.repo;

import com.cosmo.vendorservice.businesshour.entity.BusinessHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {
    BusinessHours findByVendorId(Long vendorId);
}
