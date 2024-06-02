package com.cosmo.authentication.role.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "access_group_role_map")
@AllArgsConstructor
@NoArgsConstructor
public class AccessGroupRoleMap extends AbstractEntity {

    @JoinColumn(name = "access_group", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AccessGroup accessGroup;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @JoinColumn(name = "roles", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Roles roles;
}