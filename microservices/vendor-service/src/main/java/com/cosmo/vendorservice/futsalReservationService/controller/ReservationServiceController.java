package com.cosmo.vendorservice.futsalReservationService.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.vendorservice.futsalReservationService.model.CreateReservationModel;
import com.cosmo.vendorservice.futsalReservationService.model.FetchReservationDetails;
import com.cosmo.vendorservice.futsalReservationService.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.RESERVATION)
@RequiredArgsConstructor
public class ReservationServiceController {

    private final ReservationService reservationService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse<Object>> createReservation(@RequestBody @Valid CreateReservationModel create, Principal connectedUser) {
        return reservationService.createReservation(create,connectedUser);
    }

    @PostMapping(ApiConstant.GET_ALL_RESERVATION)
    public Mono<ApiResponse<Object>> getReservationSlots(@RequestBody @Valid FetchReservationDetails fetchReservationDetails,Principal connectedUser) {
        return reservationService.getReservationSlots(fetchReservationDetails,connectedUser);
    }
}