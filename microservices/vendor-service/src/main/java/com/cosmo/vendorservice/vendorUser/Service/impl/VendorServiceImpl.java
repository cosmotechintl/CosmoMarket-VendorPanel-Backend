package com.cosmo.vendorservice.vendorUser.Service.impl;

import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.vendorservice.vendorUser.Service.VendorService;
import com.cosmo.vendorservice.vendorUser.mapper.VendorUserMapper;
import com.cosmo.vendorservice.vendorUser.model.VendorUserRequestDto;
import com.cosmo.vendorservice.vendorUser.model.VendorUserResponseDto;
import com.cosmo.authentication.user.entity.Vendor;
import com.cosmo.authentication.user.repo.VendorRepository;
import com.cosmo.common.entity.Status;
import com.cosmo.common.exception.BadRequestException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorUserMapper vendorUserMapper;
    private final VendorRepository vendorRepository;
    private final StatusRepository statusRepository;
    @Override
    public ApiResponse<VendorUserResponseDto> addVendorUser(VendorUserRequestDto vendorUserRequestDto) {
        Vendor vendor = vendorUserMapper.dtoToEntity(vendorUserRequestDto);
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorUserResponseDto vendorUserResponseDto = vendorUserMapper.entityToDto(savedVendor);
        return ResponseUtil.getSuccessfulApiResponseWithData(vendorUserResponseDto, "Vendor created successfully");
    }

    @Override
    public ApiResponse deleteVendorUser(VendorUserRequestDto vendorUserRequestDto) {
        String username = vendorUserRequestDto.getUsername();
        Vendor vendor = vendorRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(username + " vendor does not exist"));
        Status deletedStatus = statusRepository.findByName("DELETED")
                .orElseThrow(() -> new IllegalArgumentException("Status DELETED does not exist"));
        vendor.setStatus(deletedStatus);
        vendorRepository.save(vendor);
        return ResponseUtil.getSuccessfulApiResponse("Vendor " + username + " deleted successfully");
    }

    @Override
    public ApiResponse<?> getAllVendorUsers(SearchParam searchParam) {
        Pageable pageable = PaginationUtil.getPageable(searchParam);
        Page<Vendor> pageList = vendorRepository.findAll(pageable);
        List<Vendor> allVendors = pageList.getContent();
        if (allVendors.isEmpty()) {
            return ResponseUtil.getFailureResponse("No Vendors found");
        } else {
            List<VendorUserResponseDto> vendorUserResponseDtos = allVendors.stream()
                    .map(vendorUserMapper::entityToDto)
                    .collect(Collectors.toList());
            return ResponseUtil.getSuccessfulApiResponseWithData(vendorUserResponseDtos, "Vendors fetched successfully");
        }
    }

    @Override
    public ApiResponse<?> getVendorByUsername(VendorUserRequestDto vendorUserRequestDto) {
        String username = vendorUserRequestDto.getUsername();
        Vendor vendor = vendorRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(username + " vendor does not exist"));
        VendorUserResponseDto vendorUserResponseDto = vendorUserMapper.entityToDto(vendor);
        return ResponseUtil.getSuccessfulApiResponseWithData(vendorUserResponseDto, "Vendor fetched successfully");
    }

    @Override
    public ApiResponse<VendorUserResponseDto> updateVendorUser(VendorUserRequestDto vendorUserRequestDto) {
        Vendor existingVendor = vendorRepository.findById(vendorUserRequestDto.getId())
                .orElseThrow(() -> new BadRequestException("vendorUser with id:"+vendorUserRequestDto.getId() + " does not exist"));
        Vendor updatedVendor = vendorUserMapper.updateEntityFromDto(vendorUserRequestDto, existingVendor);
        Vendor savedVendor = vendorRepository.save(updatedVendor);
        VendorUserResponseDto vendorUserResponseDto =  vendorUserMapper.entityToDto(savedVendor);
        return ResponseUtil.getSuccessfulApiResponseWithData(vendorUserResponseDto, "Vendor updated successfully");
    }
}
