package com.cosmo.authentication.role.repo;

import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.common.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessGroupRepository extends JpaRepository<AccessGroup, Long>{

    Optional<AccessGroup> findByName(String name);
}
