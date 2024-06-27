package com.cosmo.authentication.vendor.log.mapper;

import com.cosmo.authentication.vendor.log.entity.VendorDeleteLog;
import com.cosmo.authentication.vendor.log.model.VendorDeleteLogModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class VendorDeleteLogMapper {
    public VendorDeleteLog mapToEntity(VendorDeleteLogModel vendorDeleteLogModel){
        VendorDeleteLog vendorDeleteLog = new VendorDeleteLog();
        vendorDeleteLog.setRemarks(vendorDeleteLogModel.getRemarks());
        vendorDeleteLog.setVendor(vendorDeleteLogModel.getVendor());
        vendorDeleteLog.setDeletedDate(new Date());
        return vendorDeleteLog;
    }
}
