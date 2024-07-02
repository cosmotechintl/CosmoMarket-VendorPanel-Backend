package com.cosmo.vendorservice.court.log.mapper;

import com.cosmo.vendorservice.court.log.entity.CourtBlockLog;
import com.cosmo.vendorservice.court.log.model.BlockCourtLogModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BlockCourtLogMapper {
    public CourtBlockLog mapToEntity(BlockCourtLogModel blockCourtLogModel){
        CourtBlockLog courtBlockLog = new CourtBlockLog();
            courtBlockLog.setRemarks(blockCourtLogModel.getRemarks());
            courtBlockLog.setCourt(blockCourtLogModel.getCourt());
            courtBlockLog.setBlockedDate(new Date());
            return courtBlockLog;
    }
}
