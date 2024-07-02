package com.cosmo.authentication.vendor.repository;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.vendor.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long>{
    Vendor findByVendorUsers(VendorUser vendorUser);
    Optional<Vendor> findByEmail(String email);
    Optional<Vendor> findByPhoneNumber(String phoneNumber);
    Optional<Vendor> findByCode(String code);
}
