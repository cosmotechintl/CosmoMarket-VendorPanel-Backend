package com.cosmo.authentication.accessGroup.repo;

import com.cosmo.authentication.accessGroup.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
