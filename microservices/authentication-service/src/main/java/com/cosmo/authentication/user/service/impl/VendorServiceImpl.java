package com.cosmo.authentication.user.service.impl;

import com.cosmo.authentication.core.service.JwtService;
import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.common.exception.NotFoundException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.authentication.user.service.VendorService;
import com.cosmo.authentication.user.mapper.VendorUserMapper;
import com.cosmo.authentication.user.model.CreateVendorUserModel;
import com.cosmo.authentication.user.model.VendorUserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.security.Principal;
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
    public Mono<ApiResponse> createVendorUser(CreateVendorUserModel createVendorUserModel, Principal connectedUser) {
        VendorUser vendorUser = vendorUserRepository.findByUsername(connectedUser.getName())
                .orElseThrow(() -> new NotFoundException("Invalid User"));
        Long vendorId = vendorUser.getVendor().getId();
        vendorUser = vendorUserMapper.toEntity(createVendorUserModel, vendorId);
        if (vendorUser != null) {
            vendorUserRepository.save(vendorUser);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor user created"));
        } else {
            return Mono.just(ResponseUtil.getFailureResponse("Vendor user creation failed"));
        }
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

}
