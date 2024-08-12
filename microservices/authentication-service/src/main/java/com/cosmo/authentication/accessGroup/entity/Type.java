package com.cosmo.authentication.accessGroup.entity;

import com.cosmo.common.abstractEntity.AbstractEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Getter
@Setter
@Table(name = "type")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Type extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;
}
