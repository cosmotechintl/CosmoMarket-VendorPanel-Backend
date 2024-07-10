package com.cosmo.authentication.vendor.repository;


import com.cosmo.authentication.vendor.entity.VendorCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorCategoryRepository extends JpaRepository<VendorCategory,Long> {
    Optional<VendorCategory> findByName(String name);
}
