package com.cosmo.common.repo;

import com.cosmo.common.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate,Long> {
    EmailTemplate findEmailTemplateByName(String name);
}
