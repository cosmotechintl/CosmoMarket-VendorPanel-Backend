package com.cosmo.authentication.user.log.repo;

import com.cosmo.authentication.user.log.entity.VendorUserBlockLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorUserBlockLogRepository extends JpaRepository<VendorUserBlockLog, Long> {
}
