package com.cosmo.authentication.accessgroup.repo;

import com.cosmo.authentication.accessgroup.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
