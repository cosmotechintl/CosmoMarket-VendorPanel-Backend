package com.cosmo.futsalService.businesshour.repo;

import com.cosmo.futsalService.businesshour.entity.BusinessHours;
import com.cosmo.futsalService.futsal.entity.Futsal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {
    List<BusinessHours> findByVendorCode(String vendorCode);
    Optional<BusinessHours> findByVendorCodeAndDay(String vendorCode, DayOfWeek day);
}
