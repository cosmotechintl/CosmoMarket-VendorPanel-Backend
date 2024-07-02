package com.cosmo.common.service.impl;

import com.cosmo.common.entity.EmailTemplate;
import com.cosmo.common.mapper.EmailTemplateMapper;
import com.cosmo.common.model.CreateEmailTemplate;
import com.cosmo.common.repo.EmailTemplateRepository;
import com.cosmo.common.service.EmailTemplateService;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class EmailTemplateServiceImpl implements EmailTemplateService {
    private final EmailTemplateRepository emailTemplateRepository;
    private final EmailTemplateMapper emailTemplateMapper;

    @Override
    public Mono<ApiResponse> createEmailTemplate(@Valid CreateEmailTemplate createEmailTemplate, Principal connectedUser) {
        EmailTemplate emailTemplate = emailTemplateMapper.mapToEntity(createEmailTemplate);
        emailTemplateRepository.save(emailTemplate);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Email Template Created Successfully"));
    }
}
