package com.cosmo.authentication.vendor.mapper;

import com.cosmo.authentication.accessGroup.repo.AccessGroupRepository;
import com.cosmo.authentication.user.entity.VendorUser;
import com.cosmo.authentication.user.repo.VendorUserRepository;
import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.authentication.vendor.log.entity.RegistrationEmailLog;
import com.cosmo.authentication.vendor.log.mapper.RegistrationEmailLogMapper;
import com.cosmo.authentication.vendor.log.repo.RegistrationEmailLogRepository;
import com.cosmo.authentication.vendor.model.CreateVendorModel;
import com.cosmo.authentication.vendor.model.SearchVendorResponse;
import com.cosmo.authentication.vendor.model.VendorDetailDto;
import com.cosmo.authentication.vendor.model.request.UpdateVendorDetailRequest;
import com.cosmo.authentication.vendor.repository.CategoryRepository;
import com.cosmo.authentication.vendor.repository.VendorRepository;
import com.cosmo.common.constant.EmailSubjectConstant;
import com.cosmo.common.constant.StatusConstant;
import com.cosmo.common.exception.ResourceNotFoundException;
import com.cosmo.common.model.request.SendEmailRequest;
import com.cosmo.common.repository.StatusRepository;
import com.cosmo.common.service.MailService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class VendorMapper {

    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected VendorRepository vendorRepository;
    @Autowired
    protected CategoryRepository categoryRepository;
    @Autowired
    protected AccessGroupRepository accessGroupRepository;
    @Autowired
    private VendorUserRepository vendorUserRepository;
    @Autowired
    protected RegistrationEmailLogRepository registrationEmailLogRepository;
    @Autowired
    protected RegistrationEmailLogMapper registrationEmailLogMapper;
    @Autowired
    protected MailService mailService;
    public Vendor mapToEntity(CreateVendorModel createVendorModel) {
        Vendor vendor = new Vendor();
        vendor.setName(createVendorModel.getName());
        vendor.setCategory(categoryRepository.findByName(createVendorModel.getCategory().getName()).orElseThrow(()->
                new ResourceNotFoundException("Category not found")));
        vendor.setLogo(createVendorModel.getLogo());
        vendor.setAddress(createVendorModel.getAddress());
        vendor.setEmail(createVendorModel.getEmail());
        vendor.setPhoneNumber(createVendorModel.getPhoneNumber());
        vendor.setPanNumber(createVendorModel.getPanNumber());

        vendor.setActive(true);
        vendor.setCode(UUID.randomUUID().toString());
        vendor.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        vendor.setCreatedAt(new Date());
        vendor.setUpdatedAt(new Date());
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorUser vendorUser = new VendorUser();
        vendorUser.setName(createVendorModel.getVendorUser().getName());
        vendorUser.setMobileNumber(createVendorModel.getVendorUser().getMobileNumber());
        vendorUser.setEmail(createVendorModel.getVendorUser().getEmail());
        vendorUser.setAccessGroup(accessGroupRepository.findByName(createVendorModel.getVendorUser().getAccessGroup().getName()).orElseThrow(
                ()-> new ResourceNotFoundException("Access group not found")));
        vendorUser.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        vendorUser.setUsername(createVendorModel.getVendorUser().getEmail());
        vendorUser.setAdmin(true);
        vendorUser.setVendor(savedVendor);
        VendorUser savedVendorUser = vendorUserRepository.save(vendorUser);

        RegistrationEmailLog registrationEmailLog = registrationEmailLogMapper.mapToEntity(savedVendorUser);
        SendEmailRequest sendEmailRequest = new SendEmailRequest();
        sendEmailRequest.setRecipient(savedVendorUser.getEmail());
        sendEmailRequest.setSubject(EmailSubjectConstant.USER_VERIFICATION);
        sendEmailRequest.setMessage(registrationEmailLog.getMessage());
        mailService.sendEmail(sendEmailRequest);

        return vendor;
    }

    public abstract SearchVendorResponse entityToRes(Vendor vendor);

    public List<SearchVendorResponse> getVendorResponses(List<Vendor> vendors) {
        return vendors.stream().map(this::entityToRes).collect(Collectors.toList());
    }

    public abstract VendorDetailDto getVendorDetails(Vendor vendor);

    public Vendor updateVendor(UpdateVendorDetailRequest request, Vendor vendor) {
        if (request == null || vendor == null) {
            throw new IllegalArgumentException("Request and Vendor must not be null");
        }
        vendor.setName(request.getName());
        vendor.setLogo(request.getLogo());
        vendor.setAddress(request.getAddress());
        vendor.setPhoneNumber(request.getPhoneNumber());
        vendor.setCategory(categoryRepository.findByName(request.getCategory().getName()).orElseThrow(()->
                new ResourceNotFoundException("Category not found")));
        vendor.setUpdatedAt(new Date());
       Vendor savedVendor =  vendorRepository.save(vendor);

       VendorUser vendorUser= vendorUserRepository.findByVendorId(savedVendor.getId()).orElseThrow(
               ()-> new ResourceNotFoundException("vendor not found")
       );
       vendorUser.setName(request.getVendorUser().getName());
       vendorUser.setMobileNumber(request.getVendorUser().getMobileNumber());
       vendorUser.setAccessGroup(accessGroupRepository.findByName(request.getVendorUser().getAccessGroup().getName()).orElseThrow(
                ()-> new ResourceNotFoundException("Access group not found")));

       vendorUserRepository.save(vendorUser);
       return vendor;
    }
}
