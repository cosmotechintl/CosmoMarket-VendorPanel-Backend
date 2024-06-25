package com.cosmo.authentication.vendor.log.repo;

import com.cosmo.authentication.vendor.log.entity.VendorDeleteLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorDeleteLogRepository extends JpaRepository<VendorDeleteLog, Long> {
}
