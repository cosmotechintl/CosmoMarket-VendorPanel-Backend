package com.cosmo.futsalService.reserve.service;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.futsalService.reserve.model.CreateReservationModel;
import com.cosmo.futsalService.reserve.model.FetchReservationDetails;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ReservationService {
    Mono<ApiResponse> createReservation(CreateReservationModel createReservationModel);
    Mono<ApiResponse<?>> getReservationSlots( FetchReservationDetails fetchReservationDetails);
}
