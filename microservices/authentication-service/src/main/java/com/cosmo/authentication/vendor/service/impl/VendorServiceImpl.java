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
import com.cosmo.authentication.vendor.model.request.UpdateVendorRequest;
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
        Optional<Vendor> existedMobileNumber = vendorRepository.findByMobileNumber(createVendorModel.getMobileNumber());
        if (existedEmail.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("This email is already taken"));
        }
        if (existedMobileNumber.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("The mobile number is already in use"));
        }
        Vendor vendor = vendorMapper.mapToEntity(createVendorModel);
        vendorRepository.save(vendor);
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
        Optional<Vendor> vendor = vendorRepository.findByEmail(deleteVendorRequest.getEmail());
        if (vendor.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor not found"));
        } else {
            Vendor vendor1 = vendor.get();
            if (StatusConstant.BLOCKED.getName().equals(vendor1.getStatus().getName()) || StatusConstant.DELETED.getName().equals(vendor1.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Vendor not found"));
            }
            vendor1.setStatus(statusRepository.findByName(StatusConstant.DELETED.getName()));
            vendor1.setActive(false);
            vendorRepository.save(vendor1);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor deleted successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> blockVendor(BlockVendorRequest blockVendorRequest) {
        Optional<Vendor> vendor = vendorRepository.findByEmail(blockVendorRequest.getEmail());
        if (vendor.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor not found"));
        } else {
            Vendor vendor1 = vendor.get();
            if (StatusConstant.DELETED.getName().equals(vendor1.getStatus().getName()) || StatusConstant.BLOCKED.getName().equals(vendor1.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Vendor not found"));
            } else {
                vendor1.setStatus(statusRepository.findByName(StatusConstant.BLOCKED.getName()));
                vendor1.setActive(false);
                vendorRepository.save(vendor1);
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor blocked successfully"));
            }
        }
    }

    @Override
    public Mono<ApiResponse<?>> unblockVendor(UnblockVendorRequest unblockVendorRequest) {
        Optional<Vendor> vendor = vendorRepository.findByEmail(unblockVendorRequest.getEmail());
        Vendor vendor1 = vendor.get();
        if (StatusConstant.BLOCKED.getName().equals(vendor1.getStatus().getName())) {
            vendor1.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
            vendor1.setActive(true);
            vendorRepository.save(vendor1);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor unblocked successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("Vendor unblock failed"));
    }

    @Override
    public Mono<ApiResponse<?>> updateVendor(UpdateVendorRequest updateVendorRequest) {
        Optional<Vendor> existedNumber= vendorRepository.findByMobileNumber(updateVendorRequest.getMobileNumber());
        if (existedNumber.isPresent() && !existedNumber.get().getEmail().equals(updateVendorRequest.getEmail())){
            return Mono.just(ResponseUtil.getFailureResponse("The mobile number is linked to another vendor"));
        }
        Optional<Vendor> vendor= vendorRepository.findByEmail(updateVendorRequest.getEmail());
        Vendor vendor1= vendor.get();
        if(StatusConstant.BLOCKED.getName().equals(vendor1.getStatus().getName()) || StatusConstant.DELETED.getName().equals(vendor1.getStatus().getName())){
            return Mono.just(ResponseUtil.getNotFoundResponse("Vendor not found"));
        }
        else {
            Vendor updatedVendor = vendorMapper.updateVendor(updateVendorRequest,vendor.get());
            vendorRepository.save(updatedVendor);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Vendor updated Successfully"));
        }
    }

}
