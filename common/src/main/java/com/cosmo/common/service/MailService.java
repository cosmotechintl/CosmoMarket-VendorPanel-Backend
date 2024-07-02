package com.cosmo.common.service;

import com.cosmo.common.model.request.SendEmailRequest;

public interface MailService {
    void sendEmail(SendEmailRequest sendEmailRequest);
}
