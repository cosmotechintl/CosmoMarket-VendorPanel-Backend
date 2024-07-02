package com.cosmo.futsalService.court.mapper;

import com.cosmo.futsalService.court.entity.Court;
import com.cosmo.futsalService.court.model.SearchCourtResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CourtsMapper {

    public abstract SearchCourtResponse entityToRes(Court court);

    public List<SearchCourtResponse> getCourtResponses(List<Court> court) {
        return court.stream().map(this::entityToRes).collect(Collectors.toList());
    }
}
