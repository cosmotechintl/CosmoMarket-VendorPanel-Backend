package com.cosmo.authentication.role.repo;

import com.cosmo.authentication.role.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
}
