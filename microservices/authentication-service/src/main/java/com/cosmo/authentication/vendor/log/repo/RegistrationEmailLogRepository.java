package com.cosmo.authentication.vendor.log.repo;

import com.cosmo.authentication.vendor.log.entity.RegistrationEmailLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationEmailLogRepository extends JpaRepository<RegistrationEmailLog, Long> {
}
