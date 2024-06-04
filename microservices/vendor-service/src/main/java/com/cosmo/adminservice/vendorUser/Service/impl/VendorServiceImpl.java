package com.cosmo.adminservice.vendorUser.Service.impl;

import com.cosmo.adminservice.vendorUser.Service.VendorService;
import com.cosmo.adminservice.vendorUser.mapper.VendorUserMapper;
import com.cosmo.adminservice.vendorUser.model.VendorUserRequestDto;
import com.cosmo.adminservice.vendorUser.model.VendorUserResponseDto;
import com.cosmo.authentication.user.entity.Vendor;
import com.cosmo.authentication.user.repo.VendorRepository;
import com.cosmo.common.entity.Status;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorUserMapper vendorUserMapper;
    private final VendorRepository vendorRepository;
    private final StatusRepository statusRepository;
    @Override
    public Object addVendorUser(VendorUserRequestDto vendorUserRequestDto) {
        Vendor vendor = vendorUserMapper.dtoToEntity(vendorUserRequestDto);
        Vendor savedVendor = vendorRepository.save(vendor);
        return vendorUserMapper.entityToDto(savedVendor);
    }

    @Override
    public void deleteVendorUser(String username) {
        Vendor vendor = vendorRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(username + "vendor does not exist"));
        Status deletedStatus = statusRepository.findByName("DELETED")
                .orElseThrow(() -> new IllegalArgumentException("Status DELETED does not exist"));
        vendor.setStatus(deletedStatus);
        vendorRepository.save(vendor);
    }

    @Override
    public ApiResponse<?> getAllVendorUsers(SearchParam searchParam) {
        Pageable pageable = PaginationUtil.getPageable(searchParam);
        Page<Vendor> pageList = vendorRepository.findAll(pageable);
        List<Vendor> allVendors = pageList.getContent();
        if (allVendors.isEmpty()) {
            return ApiResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message("No vendor found").build();
        } else {
            List<VendorUserResponseDto> vendorUserResponseDtos = allVendors.stream()
                    .map(vendorUserMapper::entityToDto)
                    .collect(Collectors.toList());
            return ApiResponse.builder().httpStatus(HttpStatus.OK).message("Vendors fetched successfully").data(vendorUserResponseDtos).build();
        }
    }



}
