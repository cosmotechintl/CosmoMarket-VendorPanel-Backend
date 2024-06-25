package com.cosmo.vendorservice.court.mapper;

import com.cosmo.common.repository.StatusRepository;
import com.cosmo.vendorservice.core.constant.StatusConstant;
import com.cosmo.vendorservice.court.entity.CourtDetails;
import com.cosmo.vendorservice.court.model.CourtDetailsDto;
import com.cosmo.vendorservice.court.model.CreateCourtRequestModel;
import com.cosmo.vendorservice.court.model.UpdateCourtRequest;
import com.cosmo.common.entity.Status;
import com.cosmo.vendorservice.court.model.request.SearchCourtResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CourtMapper {

        @Autowired
        StatusRepository statusRepository;

    public abstract CourtDetailsDto getCourtDetails(CourtDetails court);
    public abstract SearchCourtResponse entityToRes(CourtDetails courtDetails);

    public List<SearchCourtResponse> getCourtResponses(List<CourtDetails> court) {
        return court.stream().map(this::entityToRes).collect(Collectors.toList());
    }

     public CourtDetails toEntity(CreateCourtRequestModel model) {
        CourtDetails courtDetails = new CourtDetails();
        courtDetails.setName(model.getName());
        courtDetails.setImage(model.getImage());
        courtDetails.setDescription(model.getDescription());
        courtDetails.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        return courtDetails;
    }

    public static void updateEntity(CourtDetails entity, UpdateCourtRequest model) {
        entity.setName(model.getName());
        entity.setImage(model.getImage());
        entity.setDescription(model.getDescription());

    }
}
