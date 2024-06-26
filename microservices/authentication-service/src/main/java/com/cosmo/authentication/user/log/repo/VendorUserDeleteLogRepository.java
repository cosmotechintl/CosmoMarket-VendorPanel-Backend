package com.cosmo.authentication.user.log.repo;

import com.cosmo.authentication.user.log.entity.VendorUserDeleteLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorUserDeleteLogRepository extends JpaRepository<VendorUserDeleteLog, Long> {
}
