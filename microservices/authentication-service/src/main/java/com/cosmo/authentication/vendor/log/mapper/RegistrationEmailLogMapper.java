package com.cosmo.authentication.vendor.log.mapper;

import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.authentication.vendor.log.entity.RegistrationEmailLog;
import com.cosmo.authentication.vendor.log.repo.RegistrationEmailLogRepository;
import com.cosmo.common.constant.EmailTemplateConstant;
import com.cosmo.common.util.CodeUtil;
import com.cosmo.common.util.EmailContentUtil;
import com.cosmo.common.util.ExpirationTimeUtil;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class RegistrationEmailLogMapper {
    @Autowired
    protected EmailContentUtil emailContentUtil;
    @Autowired
    protected RegistrationEmailLogRepository registrationEmailLogRepository;

    public RegistrationEmailLog mapToEntity(VendorUser vendorUser){
        String code = CodeUtil.generateCode();
        Date expirationTime = ExpirationTimeUtil.getExpirationTime();
        String emailContent = emailContentUtil.prepareEmailContent(vendorUser.getName(),EmailTemplateConstant.REGISTRATION_MAIL,expirationTime,code );

        RegistrationEmailLog registrationEmailLog = new RegistrationEmailLog();
        registrationEmailLog.setEmail(vendorUser.getEmail());
        registrationEmailLog.setVendorUser(vendorUser);
        registrationEmailLog.setMessage(emailContent);
        registrationEmailLog.setUuid(code);
        registrationEmailLog.setExpired(false);
        registrationEmailLogRepository.save(registrationEmailLog);
        return registrationEmailLog;
    }
}
