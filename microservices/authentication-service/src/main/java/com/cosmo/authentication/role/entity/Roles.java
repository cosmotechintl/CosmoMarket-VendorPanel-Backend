package com.cosmo.authentication.role.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "icon")
    private String icon;

    @Column(name = "navigation")
    private String navigation;

    @Column(name = "position")
    private Integer position;

    @Column(name = "ui_group_name")
    private String uiGroupName;

    @JoinColumn(name = "parent_role", referencedColumnName = "id")
    @ManyToOne
    private Roles parentRole;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "permission")
    private String permission;

}
