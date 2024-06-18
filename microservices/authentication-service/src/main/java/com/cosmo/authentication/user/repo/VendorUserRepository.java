package com.cosmo.authentication.user.repo;

import com.cosmo.authentication.user.entity.VendorUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorUserRepository extends JpaRepository<VendorUser, Long> {

    Optional<VendorUser> findByUsername(String username);

    Optional<VendorUser> findByMobileNumber(String mobileNumber);

    Optional<VendorUser> findByEmail(String email);

}