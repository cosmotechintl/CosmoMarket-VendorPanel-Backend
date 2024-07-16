package com.cosmo.futsalService.reserve.service.impl;

import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import com.cosmo.common.util.ResponseUtil;
import com.cosmo.futsalService.businesshour.entity.BusinessHours;
import com.cosmo.futsalService.businesshour.repo.BusinessHoursRepository;
import com.cosmo.futsalService.reserve.entity.ReservationDetails;
import com.cosmo.futsalService.reserve.mapper.ReservationMapper;
import com.cosmo.futsalService.reserve.model.CreateReservationModel;
import com.cosmo.futsalService.reserve.model.FetchReservationDetails;
import com.cosmo.futsalService.reserve.service.ReservationService;
import com.cosmo.futsalService.reserve.entity.TimeSlot;
import com.cosmo.futsalService.reserve.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final BusinessHoursRepository businessHoursRepository;

    @Override
    public Mono<ApiResponse> createReservation(CreateReservationModel createReservationModel) {
        try {
            ReservationDetails reservationDetails = reservationMapper.toEntity(createReservationModel);

            // Check if the reservation is within business hours
            if (!reservationMapper.isWithinBusinessHours(reservationDetails, businessHoursRepository)) {
                return Mono.just(ResponseUtil.getFailureResponse("Reservation outside business hours"));
            }
            List<ReservationDetails> overlappingReservations = reservationRepository.findOverlappingReservations(
                    createReservationModel.getVendorCode(),
                    createReservationModel.getFutsal_uuid(),
                    createReservationModel.getDate(),
                    createReservationModel.getStartTime(),
                    createReservationModel.getEndTime()
            );

            if (!overlappingReservations.isEmpty()) {
                return Mono.just(ResponseUtil.getFailureResponse("Overlapping reservation exists"));
            }

            reservationRepository.save(reservationDetails);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Reservation created successfully"));
        } catch (Exception e) {
            log.error("Error creating reservation: ", e);
            return Mono.just(ResponseUtil.getFailureResponse("Reservation creation failed"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> getReservationSlots(FetchReservationDetails fetchReservationDetails) {
        DayOfWeek dayOfWeek = fetchReservationDetails.getDate().getDayOfWeek();
        String vendorCode = fetchReservationDetails.getVendorCode();
        LocalDate date = fetchReservationDetails.getDate();

        Optional<BusinessHours> businessHoursOpt = businessHoursRepository.findByVendorCode(vendorCode)
                .stream()
                .filter(bh -> !bh.isClosed() && bh.getDay() == dayOfWeek)
                .findFirst();

        if (businessHoursOpt.isEmpty()) {
            return Mono.just(ResponseUtil.getFailureResponse("Business hours not available for the vendor on this date"));
        }

        BusinessHours businessHours = businessHoursOpt.get();
        LocalTime openingTime = businessHours.getStartTime();
        LocalTime closingTime = businessHours.getEndTime();

        List<ReservationDetails> reservations = reservationRepository.findByVendorCodeAndDateOrderByStartTimeAsc(vendorCode, date);
        List<TimeSlot> availableSlots = reservationMapper.calculateAvailableSlots(openingTime, closingTime, reservations);

        return Mono.just(ResponseUtil.getSuccessfulApiResponse(availableSlots, "Available slots retrieved successfully"));
    }

}
