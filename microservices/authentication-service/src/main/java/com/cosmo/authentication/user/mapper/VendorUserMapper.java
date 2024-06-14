package com.cosmo.authentication.user.mapper;

import com.cosmo.authentication.accessgroup.repo.AccessGroupRepository;
import com.cosmo.authentication.user.entity.Vendor;
import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.model.SearchVenderUsersResponse;
import com.cosmo.authentication.user.repo.VendorRepository;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.exception.InvalidInputException;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.authentication.user.model.CreateVendorUserModel;
import com.cosmo.authentication.user.model.VendorUserDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class VendorUserMapper {
    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected AccessGroupRepository accessGroupRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected VendorRepository vendorRepository;


    public abstract VendorUserDetailsDto getVendorUserDetailDto(VendorUser vendorUser);

//    public abstract VendorUser toEntity(CreateVendorUserModel createVendorUserModel);
public VendorUser toEntity(CreateVendorUserModel createVendorUserModel, Long vendorId){
    if ( createVendorUserModel == null ) {
        throw new InvalidInputException("Empty request");
    }
    Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
    VendorUser vendorUser= new VendorUser();

    vendorUser.setName(createVendorUserModel.getName());
    vendorUser.setMobileNumber(createVendorUserModel.getMobileNumber());
    vendorUser.setAddress(createVendorUserModel.getAddress());
    vendorUser.setEmail(createVendorUserModel.getEmail());
    vendorUser.setAccessGroup(accessGroupRepository.findByName(createVendorUserModel.getAccessGroup().getName()).orElseThrow(
            ()-> new ResourceNotFoundException("access group not found")
    ));
    vendorUser.setUsername(createVendorUserModel.getEmail());
    vendorUser.setActive(false);
    vendorUser.setStatus(statusRepository.findByName(StatusConstant.PENDING.getName()));
    vendorUser.setAdmin(false);
    vendorUser.setVendor(vendor);
    return vendorUser;
}
    public abstract SearchVenderUsersResponse entityToDto(VendorUser vendorUser);

    public List<SearchVenderUsersResponse> getVenderUsersResponses(List<VendorUser> vendorUsers) {
        return vendorUsers.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}