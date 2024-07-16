package com.cosmo.vendorservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class PropertiesFileValue {

    @Value("${futsal_service.url}")
    private String futsalServiceUrl;

    @Value("${product_service.url}")
    private String productServiceUrl;

    @Value("${business_hour_service.url}")
    private String businessHourServiceUrl;

    @Value("${futsal_reservation_service.url}")
    private String FutsalReservationServiceUrl;

}
