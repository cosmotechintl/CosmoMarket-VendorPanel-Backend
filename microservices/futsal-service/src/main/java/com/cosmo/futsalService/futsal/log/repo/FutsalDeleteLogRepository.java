package com.cosmo.futsalService.futsal.log.repo;

import com.cosmo.futsalService.futsal.log.entity.FutsalDeleteLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FutsalDeleteLogRepository extends JpaRepository<FutsalDeleteLog, Long> {
}
