package com.cosmo.authentication.role.repository;

import com.cosmo.authentication.role.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);

    @Query("Select r from Roles r where r.name not in ('ROOT')")
    List<Roles> getAllRoles();

}
