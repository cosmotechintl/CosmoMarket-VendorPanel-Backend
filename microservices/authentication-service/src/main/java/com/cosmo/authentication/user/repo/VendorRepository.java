package com.cosmo.authentication.user.repo;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long>{
    Vendor findByVendorUsers(VendorUser vendorUser);
}
