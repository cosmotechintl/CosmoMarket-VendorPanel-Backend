package com.cosmo.authentication.user.mapper;

import com.cosmo.authentication.accessGroup.repo.AccessGroupRepository;
import com.cosmo.authentication.user.model.request.SetPasswordRequest;
import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.model.CreateVendorUserModel;
import com.cosmo.authentication.user.model.SearchVendorUsersResponse;
import com.cosmo.authentication.user.model.VendorUserDetailsDto;
import com.cosmo.authentication.vendor.log.entity.RegistrationEmailLog;
import com.cosmo.authentication.vendor.log.repo.RegistrationEmailLogRepository;
import com.cosmo.authentication.vendor.repository.VendorRepository;
import com.cosmo.authentication.user.model.requestDto.UpdateVendorRequest;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.exception.ConflictException;
import com.cosmo.common.exception.InvalidInputException;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.repository.StatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class VendorUserMapper {
    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected AccessGroupRepository accessGroupRepository;
    @Autowired
    protected VendorRepository vendorRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected VendorUserRepository vendorUserRepository;
    @Autowired
    private RegistrationEmailLogRepository registrationEmailLogRepository;


    public abstract VendorUserDetailsDto getVendorUserDetailDto(VendorUser vendorUser);

    public void updateEntity(VendorUser vendorUserToUpdate, UpdateVendorRequest updateVendorRequest) {
        if (updateVendorRequest.getName() != null) {
            vendorUserToUpdate.setName(updateVendorRequest.getName());
        }
        if (updateVendorRequest.getMobileNumber() != null) {
            vendorUserToUpdate.setMobileNumber(updateVendorRequest.getMobileNumber());
        }
        if (updateVendorRequest.getAddress() != null) {
            vendorUserToUpdate.setAddress(updateVendorRequest.getAddress());
        }
        if (updateVendorRequest.getAccessGroup() != null) {
            vendorUserToUpdate.setAccessGroup(accessGroupRepository.findByName(updateVendorRequest.getAccessGroup().getName()).orElseThrow(
                    ()-> new ResourceNotFoundException("access group not found")));
        }

    }


    public VendorUser toEntity(CreateVendorUserModel createVendorUserModel, Long vendorId){
        if ( createVendorUserModel == null ) {
            throw new InvalidInputException("Empty request");
        }
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        if (vendorUserRepository.findByMobileNumber(createVendorUserModel.getMobileNumber()).isPresent()) {
            throw new ConflictException("A user with this mobile number already exists");
        }
        if (vendorUserRepository.findByEmail(createVendorUserModel.getEmail()).isPresent()) {
            throw new ConflictException("A user with this email already exists");
        }
        if (vendorUserRepository.findByUsername(createVendorUserModel.getUsername()).isPresent()) {
            throw new ConflictException("A user with this username already exists");
        }
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
    public abstract SearchVendorUsersResponse entityToDto(VendorUser vendorUser);

    public List<SearchVendorUsersResponse> getVendorUsersResponses(List<VendorUser> vendorUsers) {
        return vendorUsers.stream().map(this::entityToDto).collect(Collectors.toList());
    }
    public VendorUser setPassword(SetPasswordRequest setPasswordRequest, RegistrationEmailLog registrationEmailLog){
        Optional<VendorUser> vendorUser = vendorUserRepository.findByEmail(registrationEmailLog.getEmail());
        VendorUser existingVendorUser = vendorUser.get();
        existingVendorUser.setPassword(passwordEncoder.encode(setPasswordRequest.getPassword()));
        existingVendorUser.setPasswordChangeDate(new Date());
        existingVendorUser.setActive(true);
        vendorUserRepository.save(existingVendorUser);
        return existingVendorUser;
    }
}