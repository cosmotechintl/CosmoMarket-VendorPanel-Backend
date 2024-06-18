package com.cosmo.authentication.user.service.impl;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VendorUserHelper {

    @Autowired
    private VendorUserRepository vendorUserRepository;

    public Long getCurrentUserVendorId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();
            Optional<VendorUser> vendorUserOptional = vendorUserRepository.findByUsername(username);

            VendorUser vendorUser = vendorUserOptional.orElseThrow(() -> new IllegalStateException("User not found"));

            if (vendorUser.getVendor() != null) {
                return vendorUser.getVendor().getId();
            } else {
                throw new IllegalStateException("Vendor not found for user");
            }
        }

        throw new IllegalStateException("User not authenticated");
    }

}
