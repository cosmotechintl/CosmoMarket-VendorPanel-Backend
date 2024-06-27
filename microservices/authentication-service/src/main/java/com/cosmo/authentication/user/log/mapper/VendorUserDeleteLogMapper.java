package com.cosmo.authentication.user.log.mapper;

import com.cosmo.authentication.user.log.entity.VendorUserDeleteLog;
import com.cosmo.authentication.user.log.model.VendorUserDeleteLogModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class VendorUserDeleteLogMapper {
    public VendorUserDeleteLog mapToEntity(VendorUserDeleteLogModel vendorDeleteLogModel){
        VendorUserDeleteLog vendorDeleteLog = new VendorUserDeleteLog();
        vendorDeleteLog.setRemarks(vendorDeleteLogModel.getRemarks());
        vendorDeleteLog.setVendorUser(vendorDeleteLogModel.getVendorUser());
        vendorDeleteLog.setDeletedDate(new Date());
        return vendorDeleteLog;
    }
}
