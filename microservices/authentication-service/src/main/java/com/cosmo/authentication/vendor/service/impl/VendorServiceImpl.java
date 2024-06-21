package com.cosmo.authentication.vendor.service.impl;

import com.cosmo.authentication.vendor.model.request.BlockVendorRequest;
import com.cosmo.authentication.vendor.model.request.DeleteVendorRequest;
import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.authentication.vendor.mapper.VendorMapper;
import com.cosmo.authentication.vendor.model.CreateVendorModel;
import com.cosmo.authentication.vendor.model.FetchVendorDetail;
import com.cosmo.authentication.vendor.model.SearchVendorResponse;
import com.cosmo.authentication.vendor.model.VendorDetailDto;
import com.cosmo.authentication.vendor.model.request.UnblockVendorRequest;
import com.cosmo.authentication.vendor.model.request.UpdateVendorDetailRequest;
import com.cosmo.authentication.vendor.repository.VendorRepository;
import com.cosmo.authentication.vendor.repository.VendorSearchRepository;
import com.cosmo.authentication.vendor.service.VendorService;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.PageableResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.model.SearchResponseWithMapperBuilder;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.service.SearchResponse;
import com.cosmo.common.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;
    private final VendorSearchRepository vendorSearchRepository;
    private final SearchResponse searchResponse;
    private final StatusRepository statusRepository;

    @Override
    public Mono<ApiResponse> createVendor(CreateVendorModel createVendorModel) {
        Optional<Vendor> existedEmail = vendorRepository.findByEmail(createVendorModel.getEmail());
        Optional<Vendor> existedMobileNumber = vendorRepository.findByPhoneNumber(createVendorModel.getPhoneNumber());
        if (existedEmail.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("This email is already taken"));
        }
        if (existedMobileNumber.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("The Phone number is already in use"));
        }
        vendorMapper.mapToEntity(createVendorModel);
        return Mono.just((ResponseUtil.getSuccessfulApiResponse("Vendor created successfully")));
    }

    @Override
    public Mono<ApiResponse<?>> getAllVendors(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<Vendor, SearchVendorResponse> responseBuilder = SearchResponseWithMapperBuilder.<Vendor, SearchVendorResponse>builder()
                .count(vendorSearchRepository::count).searchData(vendorSearchRepository::getAll)
                .mapperFunction(this.vendorMapper::getVendorResponses).searchParam(searchParam)
                .build();
        PageableResponse<SearchVendorResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Vendors fetched successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> getVendorDetails(FetchVendorDetail fetchVendorDetail) {
        Optional<Vendor> vendor = vendorRepository.findByEmail(fetchVendorDetail.getEmail());
        if (vendor.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor not found"));
        } else {
            VendorDetailDto vendorDetailDto = vendorMapper.getVendorDetails(vendor.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(vendorDetailDto, "Vendor details fetched successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> deleteVendor(DeleteVendorRequest deleteVendorRequest) {
        Optional<Vendor> checkVendor = vendorRepository.findByEmail(deleteVendorRequest.getEmail());
        if (checkVendor.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor not found"));
        } else {
            Vendor vendor = checkVendor.get();
            if (StatusConstant.BLOCKED.getName().equals(vendor.getStatus().getName()) || StatusConstant.DELETED.getName().equals(vendor.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Vendor not found"));
            }
            vendor.setStatus(statusRepository.findByName(StatusConstant.DELETED.getName()));
            vendor.setActive(false);
            vendorRepository.save(vendor);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor deleted successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> blockVendor(BlockVendorRequest blockVendorRequest) {
        Optional<Vendor> checkVendor = vendorRepository.findByEmail(blockVendorRequest.getEmail());
        if (checkVendor.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor not found"));
        } else {
            Vendor vendor = checkVendor.get();
            if (StatusConstant.DELETED.getName().equals(vendor.getStatus().getName()) || StatusConstant.BLOCKED.getName().equals(vendor.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Vendor not found"));
            } else {
                vendor.setStatus(statusRepository.findByName(StatusConstant.BLOCKED.getName()));
                vendor.setActive(false);
                vendorRepository.save(vendor);
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor blocked successfully"));
            }
        }
    }

    @Override
    public Mono<ApiResponse<?>> unblockVendor(UnblockVendorRequest unblockVendorRequest) {
        Optional<Vendor> checkVendor = vendorRepository.findByEmail(unblockVendorRequest.getEmail());
        Vendor vendor = checkVendor.get();
        if (StatusConstant.BLOCKED.getName().equals(vendor.getStatus().getName())) {
            vendor.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
            vendor.setActive(true);
            vendorRepository.save(vendor);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor unblocked successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("Vendor unblock failed"));
    }

    @Override
    public Mono<ApiResponse<?>> updateVendor(UpdateVendorDetailRequest updateVendorRequest) {
        Optional<Vendor> existedNumber = vendorRepository.findByPhoneNumber(updateVendorRequest.getPhoneNumber());
        if (existedNumber.isPresent() && !existedNumber.get().getEmail().equals(updateVendorRequest.getEmail())) {
            return Mono.just(ResponseUtil.getFailureResponse("The phone number is linked to another vendor"));
        }
        Optional<Vendor> checkVendor = vendorRepository.findByEmail(updateVendorRequest.getEmail());
        if (checkVendor.isPresent()) {
            Vendor vendor = checkVendor.get();
            if (StatusConstant.BLOCKED.getName().equals(vendor.getStatus().getName()) || StatusConstant.DELETED.getName().equals(vendor.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Vendor not found"));
            } else {
                 vendorMapper.updateVendor(updateVendorRequest, vendor);
            }
        }
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor updated Successfully"));
    }
}
