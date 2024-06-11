package com.cosmo.authentication.accessgroup.repo;

import com.cosmo.authentication.accessgroup.entity.AccessGroupRoleMap;
import com.cosmo.authentication.role.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessGroupRoleMapRepository extends JpaRepository<AccessGroupRoleMap, Long> {


    @Query("SELECT rgm.roles FROM AccessGroupRoleMap rgm WHERE rgm.accessGroup.id = :groupId AND rgm.isActive = true ")
    List<Roles> getRolesByAccessGroup(@Param("groupId") Long groupId);
}