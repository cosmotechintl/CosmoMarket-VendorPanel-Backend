package com.cosmo.vendorservice.vendorUser.mapper;

import com.cosmo.authentication.accessgroup.repo.AccessGroupRepository;
import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.vendorservice.vendorUser.model.CreateVendorUserModel;
import com.cosmo.vendorservice.vendorUser.model.VendorUserDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class VendorUserMapper {
    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected AccessGroupRepository accessGroupRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;
    public abstract VendorUserDetailsDto getVendorUserDetailDto(VendorUser vendorUser);

    public VendorUser toEntity(CreateVendorUserModel createVendorUserModel) {
        VendorUser vendorUser = new VendorUser();
        vendorUser.setName(createVendorUserModel.getName());
        vendorUser.setUsername(createVendorUserModel.getUsername());
        vendorUser.setEmail(createVendorUserModel.getEmail());
        vendorUser.setMobileNumber(createVendorUserModel.getMobileNumber());
        vendorUser.setAddress(createVendorUserModel.getAddress());
        vendorUser.setAccessGroup(accessGroupRepository.findById(createVendorUserModel.getAccessGroupId()).orElse(null));
        vendorUser.setStatus(statusRepository.findByName(StatusConstant.PENDING.getName()));
        vendorUser.setActive(true);
        vendorUser.setPassword(passwordEncoder.encode(createVendorUserModel.getPassword()));
        return vendorUser;
    }
}