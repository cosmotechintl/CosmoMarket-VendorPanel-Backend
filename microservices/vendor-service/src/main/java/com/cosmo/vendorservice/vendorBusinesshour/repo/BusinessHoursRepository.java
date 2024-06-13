package com.cosmo.vendorservice.vendorBusinesshour.repo;

import com.cosmo.vendorservice.vendorBusinesshour.entity.BusinessHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {
    BusinessHours findByVendorId(Long vendorId);
}
