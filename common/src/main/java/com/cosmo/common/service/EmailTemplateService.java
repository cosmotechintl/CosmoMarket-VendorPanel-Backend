package com.cosmo.common.service;

import com.cosmo.common.model.CreateEmailTemplate;
import com.cosmo.common.model.ApiResponse;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface EmailTemplateService {
    Mono<ApiResponse> createEmailTemplate(CreateEmailTemplate createEmailTemplate, Principal connectedUser);
}

