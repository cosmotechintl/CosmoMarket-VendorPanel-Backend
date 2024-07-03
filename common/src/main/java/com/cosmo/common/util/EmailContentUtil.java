package com.cosmo.common.util;

import com.cosmo.common.constant.FreeMarkerTemplateConstant;
import com.cosmo.common.entity.EmailTemplate;
import com.cosmo.common.repo.EmailTemplateRepository;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmailContentUtil {
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;
    @Autowired
    private freemarker.template.Configuration freeMarkerConfig;

    public String prepareEmailContent(String vendorUserName, String templateName, Date expirationTime, String code){
        EmailTemplate emailTemplate = emailTemplateRepository.findEmailTemplateByName(templateName);
        Map<String, Object> model = new HashMap<>();
        model.put(FreeMarkerTemplateConstant.USERNAME, vendorUserName);
        model.put(FreeMarkerTemplateConstant.EXPIRATION_TIME, expirationTime);
        model.put(FreeMarkerTemplateConstant.VERIFICATION_LINK, "http://localhost:3000/set-password/"+code);
        model.put(FreeMarkerTemplateConstant.CURRENT_YEAR, Year.now().getValue());

        String emailContent;

        try {
            Template template = new Template("emailTemplate", emailTemplate.getTemplate(), freeMarkerConfig);
            emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            throw new RuntimeException("Error processing email template: "+ e.getMessage());
        }

        return emailContent;
    }
}
