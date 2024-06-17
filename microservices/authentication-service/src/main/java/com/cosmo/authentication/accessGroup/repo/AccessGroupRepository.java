package com.cosmo.authentication.accessGroup.repo;

import com.cosmo.authentication.accessGroup.entity.AccessGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessGroupRepository extends JpaRepository<AccessGroup,Long> {
    Optional<AccessGroup> findByName(String name);

}
