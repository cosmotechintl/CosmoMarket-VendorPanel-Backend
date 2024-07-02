package com.cosmo.futsalService.futsal.log.mapper;


import com.cosmo.futsalService.futsal.log.entity.FutsalBlockLog;
import com.cosmo.futsalService.futsal.log.entity.FutsalDeleteLog;
import com.cosmo.futsalService.futsal.log.model.FutsalBlockLogModel;
import com.cosmo.futsalService.futsal.log.model.FutsalDeleteLogModel;
import com.cosmo.futsalService.futsal.log.repo.FutsalBlockLogRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class FutsalLogMapper {
    @Autowired
    private FutsalBlockLogRepository futsalBlockLogRepository;
    public FutsalBlockLog mapToEntity(FutsalBlockLogModel futsalBlockLogModel){
                FutsalBlockLog futsalBlockLog = new FutsalBlockLog();
                futsalBlockLog.setRemarks(futsalBlockLogModel.getRemarks());
                futsalBlockLog.setFutsal(futsalBlockLogModel.getFutsal());
                futsalBlockLog.setBlockedDate(new Date());
                return futsalBlockLogRepository.save(futsalBlockLog);
    }

    public FutsalDeleteLog mapToEntity(FutsalDeleteLogModel futsalDeleteLogModel){
        FutsalDeleteLog futsalDeleteLog = new FutsalDeleteLog();
        futsalDeleteLog.setRemarks(futsalDeleteLogModel.getRemarks());
        futsalDeleteLog.setFutsal(futsalDeleteLogModel.getFutsal());
        futsalDeleteLog.setDeletedDate(new Date());
        return futsalDeleteLog;
    }
}
