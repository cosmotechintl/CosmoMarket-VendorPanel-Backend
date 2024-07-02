package com.cosmo.futsalService.futsal.log.repo;

import com.cosmo.futsalService.futsal.log.entity.FutsalBlockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutsalBlockLogRepository extends JpaRepository<FutsalBlockLog, Long> {
}
