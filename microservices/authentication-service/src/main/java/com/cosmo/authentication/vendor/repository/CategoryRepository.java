package com.cosmo.authentication.vendor.repository;


import com.cosmo.authentication.vendor.entity.Category;
import io.netty.util.internal.ObjectPool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
}
