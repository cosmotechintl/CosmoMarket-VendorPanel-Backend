package com.cosmo.authentication.user.log.mapper;

import com.cosmo.authentication.user.log.entity.VendorUserBlockLog;
import com.cosmo.authentication.user.log.model.VendorUserBlockLogModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class VendorUserBlockLogMapper {
    public VendorUserBlockLog mapToEntity(VendorUserBlockLogModel vendorBlockLogModel){
        VendorUserBlockLog vendorBlockLog = new VendorUserBlockLog();
        vendorBlockLog.setRemarks(vendorBlockLogModel.getRemarks());
        vendorBlockLog.setVendorUser(vendorBlockLogModel.getVendorUser());
        vendorBlockLog.setBlockedDate(new Date());
        return vendorBlockLog;
    }
}
