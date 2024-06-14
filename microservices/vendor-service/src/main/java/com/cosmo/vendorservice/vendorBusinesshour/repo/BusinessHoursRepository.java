package com.cosmo.vendorservice.vendorBusinesshour.repo;

import com.cosmo.vendorservice.vendorBusinesshour.entity.BusinessHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {

    List<BusinessHours> findByVendorId(Long vendorId);

}
