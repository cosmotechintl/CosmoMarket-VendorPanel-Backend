package com.cosmo.futsalService.futsal.service.impl;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.futsalService.futsal.entity.Futsal;
import com.cosmo.futsalService.futsal.mapper.FutsalMapper;
import com.cosmo.futsalService.futsal.model.CreateFutsalModel;
import com.cosmo.futsalService.futsal.model.FutsalDto;
import com.cosmo.futsalService.futsal.model.request.FetchFutsalDetail;
import com.cosmo.futsalService.futsal.repo.FutsalRepository;
import com.cosmo.futsalService.futsal.service.FutsalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.security.Principal;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FutsalServiceImpl implements FutsalService {
    private final FutsalRepository futsalRepository;
    private final VendorUserRepository vendorUserRepository;
    private final FutsalMapper futsalMapper;
    @Override
    public Mono<ApiResponse> createFutsal(CreateFutsalModel createFutsalModel, Principal connectedUser) {
        Optional<VendorUser> checkVendorUser = vendorUserRepository.findByUsername(connectedUser.getName());
        VendorUser vendorUser = checkVendorUser.get();
        String code = vendorUser.getVendor().getCode();
       Futsal futsal = futsalMapper.mapToEntity(createFutsalModel,code);
        futsalRepository.save(futsal);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Futsal created successfull"));
    }

    @Override
    public Mono<ApiResponse<?>> getFutsalDetails(FetchFutsalDetail fetchFutsalDetail) {
        Optional<Futsal> checkFutsal = futsalRepository.findByVendorCode(fetchFutsalDetail.getVendorCode());
        if(checkFutsal.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Futsal not found"));
        }
        else {
            FutsalDto futsalDto = futsalMapper.getFutsalDetails(checkFutsal.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(futsalDto, "Futsal details fetched successfully"));
        }
    }
}
