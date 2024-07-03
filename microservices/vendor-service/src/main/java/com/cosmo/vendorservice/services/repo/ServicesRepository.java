package com.cosmo.vendorservice.services.repo;

import com.cosmo.vendorservice.services.entity.Services_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services_Details, Long> {

}
