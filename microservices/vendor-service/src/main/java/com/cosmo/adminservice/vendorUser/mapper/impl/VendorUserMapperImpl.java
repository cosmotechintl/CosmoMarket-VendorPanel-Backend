package com.cosmo.adminservice.vendorUser.mapper.impl;

import com.cosmo.adminservice.vendorUser.mapper.VendorUserMapper;
import com.cosmo.adminservice.vendorUser.model.VendorUserRequestDto;
import com.cosmo.adminservice.vendorUser.model.VendorUserResponseDto;
import com.cosmo.authentication.role.entity.AccessGroup;
import com.cosmo.authentication.role.repo.AccessGroupRepository;
import com.cosmo.authentication.user.entity.Vendor;
import com.cosmo.authentication.user.repo.VendorRepository;
import com.cosmo.common.entity.Status;
import com.cosmo.common.exception.BadRequestException;
import com.cosmo.common.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class VendorUserMapperImpl implements VendorUserMapper {
    private final PasswordEncoder passwordEncoder;
    private final StatusRepository statusRepository;
    private final AccessGroupRepository accessGroupRepository;
    private final VendorRepository vendorRepository;
    @Override
    public Vendor dtoToEntity(VendorUserRequestDto vendorUserRequestDto) {
        if (vendorUserRequestDto == null) {
            return null;
        }
        Vendor vendor = new Vendor();
        vendor.setName(vendorUserRequestDto.getName());
        vendor.setPassword(passwordEncoder.encode(vendorUserRequestDto.getPassword()));
        if (vendorRepository.existsByUsername(vendorUserRequestDto.getUsername())) {
            throw new BadRequestException("Username already exists");
        }else {
            vendor.setUsername(vendorUserRequestDto.getUsername());
        }
        vendor.setActive(vendorUserRequestDto.isActive());
        vendor.setEmail(vendorUserRequestDto.getEmail());
        if (vendorUserRequestDto.getStatusId() != null) {
            Status status = statusRepository.findById(vendorUserRequestDto.getStatusId())
                    .orElseThrow(() -> new IllegalArgumentException(vendorUserRequestDto.getStatusId() + "status does not exist"));
            vendor.setStatus(status);
        } else {
            throw new IllegalArgumentException("StatusId cannot be null");
        }
        if(vendorUserRequestDto.getAccessGroupId() !=null){
            AccessGroup accessGroup = accessGroupRepository.findById(vendorUserRequestDto.getAccessGroupId())
                    .orElseThrow(() -> new IllegalArgumentException(vendorUserRequestDto.getAccessGroupId() + "access group does not exist"));
            vendor.setAccessGroup(accessGroup);
        }
        else {
            throw new IllegalArgumentException("AccessGroupId cannot be null");
        }
        vendor.setMobileNumber(vendorUserRequestDto.getMobileNumber());
        vendor.setAddress(vendorUserRequestDto.getAddress());
        vendor.setTwoFactorEnabled(vendorUserRequestDto.isTwoFactorEnabled());
        vendor.setSuperAdmin(vendorUserRequestDto.isSuperAdmin());
        return vendor;
    }

    @Override
    public VendorUserResponseDto entityToDto(Vendor vendor) {
        if (vendor == null) {
            return null;
        }
        VendorUserResponseDto vendorUserResponseDto = new VendorUserResponseDto();
        vendorUserResponseDto.setName(vendor.getName());
        vendorUserResponseDto.setUsername(vendor.getUsername());
        vendorUserResponseDto.setActive(vendor.isActive());
        vendorUserResponseDto.setEmail(vendor.getEmail());
        vendorUserResponseDto.setMobileNumber(vendor.getMobileNumber());
        vendorUserResponseDto.setAddress(vendor.getAddress());
        vendorUserResponseDto.setStatusName(vendor.getStatus().getName());
        vendorUserResponseDto.setAccessGroupName(vendor.getAccessGroup().getName());
        vendorUserResponseDto.setProfilePictureName(vendor.getProfilePictureName());
        vendorUserResponseDto.setTwoFactorEnabled(vendor.isTwoFactorEnabled());
        vendorUserResponseDto.setSuperAdmin(vendor.isSuperAdmin());
        return vendorUserResponseDto;
    }


}
