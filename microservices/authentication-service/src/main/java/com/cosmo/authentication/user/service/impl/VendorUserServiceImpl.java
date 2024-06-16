package com.cosmo.authentication.user.service.impl;

import com.cosmo.authentication.core.service.JwtService;
import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.mapper.VendorUserMapper;
import com.cosmo.authentication.user.model.CreateVendorUserModel;
import com.cosmo.authentication.user.model.PasswordChangeRequest;
import com.cosmo.authentication.user.model.SearchVendorUsersResponse;
import com.cosmo.authentication.user.model.VendorUserDetailsDto;
import com.cosmo.authentication.user.model.requestDto.DeleteVendorRequest;
import com.cosmo.authentication.user.model.requestDto.UpdateVendorRequest;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.authentication.user.repo.VendorUsersSearchRepository;
import com.cosmo.authentication.user.service.VendorUserService;
import com.cosmo.common.exception.NotFoundException;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorUserServiceImpl implements VendorUserService {
    private final VendorUserMapper vendorUserMapper;
    private final VendorUserRepository vendorUserRepository;
    private final StatusRepository statusRepository;
    private final JwtService jwtService;
    private final VendorUsersSearchRepository vendorUsersSearchRepository;
    private final SearchResponse searchResponse;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<ApiResponse<?>> getallVendorUserDetail(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<VendorUser, SearchVendorUsersResponse> responseBuilder =
                SearchResponseWithMapperBuilder.<VendorUser, SearchVendorUsersResponse>builder()
                        .count(vendorUsersSearchRepository::count)
//                        .searchData(vendorUsersSearchRepository::getAll)
                        .searchData(param -> (List<VendorUser>) vendorUsersSearchRepository.getAll(param))
                        .mapperFunction(this.vendorUserMapper::getVendorUsersResponses)
                        .searchParam(searchParam)
                        .build();
        PageableResponse<SearchVendorUsersResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Vendor users fetched successfully."));
    }
    @Override
    public Mono<ApiResponse<?>> changePassword(PasswordChangeRequest passwordChangeRequest, Principal connectedUser) {
        var vendor= ((VendorUser) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        if (!passwordEncoder.matches(passwordChangeRequest.getOldPassword(),vendor.getPassword())){
            return Mono.just(ResponseUtil.getFailureResponse("old password is incorrect"));
        }
        if(!passwordChangeRequest.getNewPassword().equals((passwordChangeRequest.getRetypeNewPassword()))){
            return Mono.just(ResponseUtil.getFailureResponse("new passwords do not match"));
        }
        else {
            vendor.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
            vendor.setPasswordChangeDate(new Date());
            vendorUserRepository.save(vendor);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("password changed successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> updateVendorUser(UpdateVendorRequest updateVendorRequest, Principal connectedUser) {
        return null;
    }

    @Override
    public Mono<ApiResponse<?>> deleteVendorUser(DeleteVendorRequest deleteVendorRequest, Principal connectedUser) {
        return null;
    }


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
