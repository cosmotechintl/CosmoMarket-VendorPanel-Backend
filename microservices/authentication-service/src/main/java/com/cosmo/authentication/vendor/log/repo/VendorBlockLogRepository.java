package com.cosmo.authentication.vendor.log.repo;

import com.cosmo.authentication.vendor.log.entity.VendorBlockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorBlockLogRepository extends JpaRepository<VendorBlockLog, Long> {
}
