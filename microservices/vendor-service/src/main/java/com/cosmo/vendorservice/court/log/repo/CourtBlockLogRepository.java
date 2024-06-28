package com.cosmo.vendorservice.court.log.repo;

import com.cosmo.vendorservice.court.log.entity.CourtBlockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtBlockLogRepository extends JpaRepository<CourtBlockLog,Long> {
}
