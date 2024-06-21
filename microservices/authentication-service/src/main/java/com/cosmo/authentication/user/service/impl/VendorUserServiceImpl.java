package com.cosmo.authentication.user.service.impl;

import com.cosmo.authentication.core.service.JwtService;
import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.mapper.VendorUserMapper;
import com.cosmo.authentication.user.model.CreateVendorUserModel;
import com.cosmo.authentication.user.model.PasswordChangeRequest;
import com.cosmo.authentication.user.model.SearchVendorUsersResponse;
import com.cosmo.authentication.user.model.VendorUserDetailsDto;
import com.cosmo.authentication.user.model.request.VendorUserDetailRequest;
import com.cosmo.authentication.user.model.requestDto.DeleteVendorRequest;
import com.cosmo.authentication.user.model.requestDto.UpdateVendorRequest;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.authentication.user.repo.VendorUsersSearchRepository;
import com.cosmo.authentication.user.service.VendorUserService;
import com.cosmo.common.constant.SearchParamConstant;
import com.cosmo.common.constant.StatusConstant;
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

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.Date;
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
    private final VendorUserHelper vendorUserHelper;

    @Override
    public Mono<ApiResponse<?>> getallVendorUserDetail(SearchParam searchParam) {
        long vendorId = vendorUserHelper.getCurrentUserVendorId();
        searchParam.getParam().put(SearchParamConstant.VENDOR_ID, vendorId);
        SearchResponseWithMapperBuilder<VendorUser, SearchVendorUsersResponse> responseBuilder =
                SearchResponseWithMapperBuilder.<VendorUser, SearchVendorUsersResponse>builder()
                        .count(vendorUsersSearchRepository::count)
                        .searchData(vendorUsersSearchRepository::getAll)
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
    @Transactional
    public Mono<ApiResponse<?>> updateVendorUser(UpdateVendorRequest updateVendorRequest, Principal connectedUser) {
        VendorUser adminUser = vendorUserRepository.findByUsername(connectedUser.getName())
                .orElseThrow(() -> new NotFoundException("Invalid User"));
        VendorUser vendorUserToUpdate = vendorUserRepository.findByEmail(updateVendorRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("Vendor user not found"));
        if (!adminUser.getVendor().getId().equals(vendorUserToUpdate.getVendor().getId())) {
            return Mono.just(ResponseUtil.getFailureResponse("Admin user does not have permission to update this vendor user"));
        }
        if (!vendorUserToUpdate.getMobileNumber().equals(updateVendorRequest.getMobileNumber())) {
            Optional<VendorUser> existedNumber = vendorUserRepository.findByMobileNumber(updateVendorRequest.getMobileNumber());
            if (existedNumber.isPresent()) {
                return Mono.just(ResponseUtil.getFailureResponse("The mobile number is linked to another account."));
            }
        }
        if (StatusConstant.BLOCKED.getName().equals(vendorUserToUpdate.getStatus().getName()) || StatusConstant.DELETED.getName().equals(vendorUserToUpdate.getStatus().getName())) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        }
        vendorUserMapper.updateEntity(vendorUserToUpdate, updateVendorRequest);
        vendorUserRepository.save(vendorUserToUpdate);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor user updated"));
    }

    @Override
    @Transactional
    public Mono<ApiResponse<?>> deleteVendorUser(DeleteVendorRequest deleteVendorRequest, Principal connectedUser) {
        Optional<VendorUser> vendorAdminUser = vendorUserRepository.findByUsername(connectedUser.getName());
        if (vendorAdminUser.isEmpty()) {
            return Mono.just(ResponseUtil.getFailureResponse("Invalid User"));
        }
        VendorUser adminUser = vendorAdminUser.get();
        Optional<VendorUser> vendorUserToDeleteOptional = vendorUserRepository.findByEmail(deleteVendorRequest.getEmail());
        if (vendorUserToDeleteOptional.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        }
        VendorUser vendorUserToDelete = vendorUserToDeleteOptional.get();
        if (!adminUser.getVendor().getId().equals(vendorUserToDelete.getVendor().getId())) {
            return Mono.just(ResponseUtil.getFailureResponse("Admin user does not have permission to delete this vendor user"));
        }
        if (StatusConstant.BLOCKED.getName().equals(vendorUserToDelete.getStatus().getName()) || StatusConstant.DELETED.getName().equals(vendorUserToDelete.getStatus().getName())) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        }
        vendorUserToDelete.setStatus(statusRepository.findByName(StatusConstant.DELETED.getName()));
        vendorUserRepository.save(vendorUserToDelete);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor user deleted successfully"));
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
    public Mono<ApiResponse<?>> getVendorUserDetail(Principal connectedUser) {
        Optional<VendorUser> vendorUser = vendorUserRepository.findByUsername(connectedUser.getName());
        if (vendorUser.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        } else {
            VendorUserDetailsDto vendorUserDetailsDto = vendorUserMapper.getVendorUserDetailDto(vendorUser.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(vendorUserDetailsDto, "Vendor user fetched successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> getVendorUserDetails(VendorUserDetailRequest vendorUserDetailRequest) {
        Optional<VendorUser> vendorUser = vendorUserRepository.findByEmail(vendorUserDetailRequest.getEmail());
        if (vendorUser.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor user not found"));
        } else {
            VendorUserDetailsDto vendorUserDetailsDto = vendorUserMapper.getVendorUserDetailDto(vendorUser.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(vendorUserDetailsDto, "Vendor user fetched successfully"));
        }
    }

}
