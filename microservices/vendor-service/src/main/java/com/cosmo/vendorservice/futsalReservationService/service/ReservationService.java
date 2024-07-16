package com.cosmo.vendorservice.futsalReservationService.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.vendorservice.futsalReservationService.model.CreateReservationModel;
import com.cosmo.vendorservice.futsalReservationService.model.FetchReservationDetails;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface ReservationService {
    Mono<ApiResponse<Object>> createReservation(CreateReservationModel createReservationModel,Principal connectedUser);
    Mono<ApiResponse<Object>> getReservationSlots( FetchReservationDetails fetchReservationDetails, Principal connectedUser);
}
