package com.cosmo.authentication.vendor.log.mapper;


import com.cosmo.authentication.vendor.log.entity.VendorBlockLog;
import com.cosmo.authentication.vendor.log.model.VendorBlockLogModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class VendorBlockLogMapper {
    public VendorBlockLog mapToEntity(VendorBlockLogModel vendorBlockLogModel){
                VendorBlockLog vendorBlockLog = new VendorBlockLog();
                vendorBlockLog.setRemarks(vendorBlockLogModel.getRemarks());
                vendorBlockLog.setVendor(vendorBlockLogModel.getVendor());
                vendorBlockLog.setBlockedDate(new Date());
                return vendorBlockLog;
    }
}
