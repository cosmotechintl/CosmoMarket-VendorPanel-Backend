package com.cosmo.authentication.user.repo;

import com.cosmo.authentication.user.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Optional<Vendor> findByUsername(String username);

    void deleteByUsername(String username);

    boolean existsByUsername(String username);
}