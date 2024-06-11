package com.cosmo.vendorservice.vendorUser.Service.impl;

import com.cosmo.authentication.core.service.JwtService;
import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.vendorservice.vendorUser.Service.VendorService;
import com.cosmo.vendorservice.vendorUser.mapper.VendorUserMapper;
import com.cosmo.vendorservice.vendorUser.model.CreateVendorUserModel;
import com.cosmo.vendorservice.vendorUser.model.requestDto.FetchVendorUserDetails;
import com.cosmo.vendorservice.vendorUser.model.VendorUserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorUserMapper vendorUserMapper;
    private final VendorUserRepository vendorUserRepository;
    private final StatusRepository statusRepository;
    private final JwtService jwtService;

//    @Override
//    public ApiResponse<VendorUserResponseDto> addVendorUser(VendorUserRequestDto vendorUserRequestDto) {
//        Vendor vendor = vendorUserMapper.dtoToEntity(vendorUserRequestDto);
//        Vendor savedVendor = vendorRepository.save(vendor);
//        VendorUserResponseDto vendorUserResponseDto = vendorUserMapper.entityToDto(savedVendor);
//        return ResponseUtil.getSuccessfulApiResponseWithData(vendorUserResponseDto, "Vendor created successfully");
//    }
//
//    @Override
//    public ApiResponse deleteVendorUser(VendorUserRequestDto vendorUserRequestDto) {
//        String username = vendorUserRequestDto.getUsername();
//        Vendor vendor = vendorRepository.findByUsername(username)
//                .orElseThrow(() -> new ResourceNotFoundException(username + " vendor does not exist"));
//        Status deletedStatus = statusRepository.findByName("DELETED")
//                .orElseThrow(() -> new IllegalArgumentException("Status DELETED does not exist"));
//        vendor.setStatus(deletedStatus);
//        vendorRepository.save(vendor);
//        return ResponseUtil.getSuccessfulApiResponse("Vendor " + username + " deleted successfully");
//    }
//
//    @Override
//    public ApiResponse<?> getAllVendorUsers(SearchParam searchParam) {
//        Pageable pageable = PaginationUtil.getPageable(searchParam);
//        Page<Vendor> pageList = vendorRepository.findAll(pageable);
//        List<Vendor> allVendors = pageList.getContent();
//        if (allVendors.isEmpty()) {
//            return ResponseUtil.getFailureResponse("No Vendors found");
//        } else {
//            List<VendorUserResponseDto> vendorUserResponseDtos = allVendors.stream()
//                    .map(vendorUserMapper::entityToDto)
//                    .collect(Collectors.toList());
//            return ResponseUtil.getSuccessfulApiResponseWithData(vendorUserResponseDtos, "Vendors fetched successfully");
//        }
//    }

    @Override
    @Transactional
    public Mono<ApiResponse> createVendorUser(CreateVendorUserModel createVendorUserModel) {
        VendorUser vendorUser = vendorUserMapper.toEntity(createVendorUserModel);
        vendorUserRepository.save(vendorUser);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor user created"));
    }

    @Override
    public Mono<ApiResponse<?>> getVendorUserDetail(
            String token) {
        Optional<VendorUser> vendorUser = vendorUserRepository.findByUsername(jwtService.extractUsername(token));
        if (vendorUser.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        } else {
            VendorUserDetailsDto vendorUserDetailsDto = vendorUserMapper.getVendorUserDetailDto(vendorUser.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(vendorUserDetailsDto, "Vendor user fetched successfully"));
        }
    }

//    @Override
//    public ApiResponse<VendorUserResponseDto> updateVendorUser(VendorUserRequestDto vendorUserRequestDto) {
//        Vendor existingVendor = vendorRepository.findById(vendorUserRequestDto.getId())
//                .orElseThrow(() -> new BadRequestException("vendorUser with id:"+vendorUserRequestDto.getId() + " does not exist"));
//        Vendor updatedVendor = vendorUserMapper.updateEntityFromDto(vendorUserRequestDto, existingVendor);
//        Vendor savedVendor = vendorRepository.save(updatedVendor);
//        VendorUserResponseDto vendorUserResponseDto =  vendorUserMapper.entityToDto(savedVendor);
//        return ResponseUtil.getSuccessfulApiResponseWithData(vendorUserResponseDto, "Vendor updated successfully");
//    }
}
