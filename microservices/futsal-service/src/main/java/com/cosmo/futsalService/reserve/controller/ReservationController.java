package com.cosmo.futsalService.reserve.controller;

import com.cosmo.common.constant.ApiConstant;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.futsalService.reserve.model.CreateReservationModel;
import com.cosmo.futsalService.reserve.model.FetchReservationDetails;
import com.cosmo.futsalService.reserve.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping(ApiConstant.RESERVATION)
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> createReservation(@RequestBody @Valid CreateReservationModel create){
        return reservationService.createReservation(create);
    }

    @PostMapping(ApiConstant.GET_ALL_RESERVATION)
    public Mono<ApiResponse<?>> getReservationSlots( @RequestBody @Valid FetchReservationDetails fetchReservationDetails){
        return reservationService.getReservationSlots(fetchReservationDetails);
    }

}
