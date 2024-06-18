package com.cosmo.authentication.vendor.mapper;

import com.cosmo.authentication.accessGroup.repo.AccessGroupRepository;
import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.authentication.vendor.model.CreateVendorModel;
import com.cosmo.authentication.vendor.model.SearchVendorResponse;
import com.cosmo.authentication.vendor.model.VendorDetailDto;
import com.cosmo.authentication.vendor.model.request.UpdateVendorRequest;
import com.cosmo.authentication.vendor.repository.CategoryRepository;
import com.cosmo.authentication.vendor.repository.VendorRepository;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.repository.StatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class VendorMapper {

    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected VendorRepository vendorRepository;
    @Autowired
    protected CategoryRepository categoryRepository;
    @Autowired
    protected AccessGroupRepository accessGroupRepository;

    public Vendor mapToEntity(CreateVendorModel createVendorModel) {
        Vendor vendor = new Vendor();
        vendor.setName(createVendorModel.getName());
        vendor.setCategory(categoryRepository.findByName(createVendorModel.getCategory().getName()).orElseThrow(()->
                new ResourceNotFoundException("Category not found")));
        vendor.setLogo(createVendorModel.getLogo());
        vendor.setAddress(createVendorModel.getAddress());
        vendor.setEmail(createVendorModel.getEmail());
        vendor.setMobileNumber(createVendorModel.getMobileNumber());
        vendor.setPanNumber(createVendorModel.getPanNumber());

        List<VendorUser> vendorUsers = createVendorModel.getVendorUser().stream().map(vendorUserDto -> {
            VendorUser vendorUser = new VendorUser();
            vendorUser.setName(vendorUserDto.getName());
            vendorUser.setMobileNumber(vendorUserDto.getMobileNumber());
            vendorUser.setEmail(vendorUserDto.getEmail());
            vendorUser.setStatus(statusRepository.findByName(StatusConstant.PENDING.getName()));
            vendorUser.setUsername(vendorUserDto.getEmail());
            vendorUser.setAccessGroup(accessGroupRepository.findByName(vendorUserDto.getAccessGroup().getName()).orElseThrow(() -> new ResourceNotFoundException("Access group not found")));
            vendorUser.setVendor(vendor);
            return vendorUser;
        }).toList();
        vendor.setVendorUsers(vendorUsers);
        vendor.setActive(true);
        vendor.setCode(UUID.randomUUID().toString());
        vendor.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        vendor.setCreatedAt(new Date());
        vendor.setUpdatedAt(new Date());
        return vendor;
    }

    public abstract SearchVendorResponse entityToRes(Vendor vendor);

    public List<SearchVendorResponse> getVendorResponses(List<Vendor> vendors) {
        return vendors.stream().map(this::entityToRes).collect(Collectors.toList());
    }

    public abstract VendorDetailDto getVendorDetails(Vendor vendor);

    public Vendor updateVendor(UpdateVendorRequest request, Vendor vendor) {
        if (request == null || vendor == null) {
            throw new IllegalArgumentException("Request and Vendor must not be null");
        }
        vendor.setName(request.getName());
        vendor.setLogo(request.getLogo());
        vendor.setAddress(request.getAddress());
        vendor.setMobileNumber(request.getMobileNumber());
        vendor.setCategory(categoryRepository.findByName(request.getCategory().getName()).orElseThrow(()->
                new ResourceNotFoundException("Category not found")));

        if (request.getVendorUser() != null) {
            List<VendorUser> updatedVendorUsers = request.getVendorUser().stream().map(userDetails -> {
                VendorUser vendorUser= vendor.getVendorUsers().stream()
                        .filter(vu -> vu.getMobileNumber().equals(userDetails.getMobileNumber()))
                        .findFirst()
                        .orElse(new VendorUser());
                vendorUser.setName(userDetails.getName());
                vendorUser.setMobileNumber(userDetails.getMobileNumber());
                vendorUser.setAccessGroup(accessGroupRepository.findByName(userDetails.getAccessGroup().getName()).orElseThrow(
                ()-> new ResourceNotFoundException("access group not found")));
                vendorUser.setVendor(vendor);
                return vendorUser;
            }).toList();

            vendor.setVendorUsers(updatedVendorUsers);
        }
        vendor.setUpdatedAt(new Date());
        return vendor;
    }
}
