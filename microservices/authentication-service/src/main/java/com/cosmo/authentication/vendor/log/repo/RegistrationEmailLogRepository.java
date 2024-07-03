package com.cosmo.authentication.vendor.log.repo;

import com.cosmo.authentication.vendor.log.entity.RegistrationEmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RegistrationEmailLogRepository extends JpaRepository<RegistrationEmailLog, Long> {
    Optional<RegistrationEmailLog> findByUuid(String uuid);
}
