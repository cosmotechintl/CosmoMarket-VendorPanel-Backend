package com.cosmo.authentication.role.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import com.cosmo.common.entity.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "access_group")
public class AccessGroup extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status", referencedColumnName = "id")
    private Status status;

    @Column(name = "is_super_admin_group", nullable = false)
    private boolean isSuperAdminGroup;

    @Column(name = "remarks")
    private String remarks;

    @OneToMany(mappedBy = "accessGroup",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccessGroupRoleMap> accessGroupRoleMaps;

}
