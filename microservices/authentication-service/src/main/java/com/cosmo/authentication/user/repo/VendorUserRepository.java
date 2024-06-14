package com.cosmo.authentication.user.repo;

import com.cosmo.authentication.user.entity.VendorUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorUserRepository extends JpaRepository<VendorUser, Long> {

    Optional<VendorUser> findByUsername(String username);

    Optional<Object> findByMobileNumber(String mobileNumber);

    Optional<Object> findByEmail(String email);

}