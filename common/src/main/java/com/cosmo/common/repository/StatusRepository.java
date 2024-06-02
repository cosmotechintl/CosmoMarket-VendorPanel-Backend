package com.cosmo.common.repository;

import com.cosmo.common.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByName(String name);

    Optional<Status> findById(Long statusId);
}
