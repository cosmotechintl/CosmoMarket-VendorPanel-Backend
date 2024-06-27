package com.cosmo.productsservice.category.repo;

import com.cosmo.productsservice.category.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findByName(String Name);
}
