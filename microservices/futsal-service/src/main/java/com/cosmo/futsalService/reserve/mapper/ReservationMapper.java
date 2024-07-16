package com.cosmo.futsalService.reserve.mapper;

import com.cosmo.futsalService.businesshour.entity.BusinessHours;
import com.cosmo.futsalService.businesshour.repo.BusinessHoursRepository;
import com.cosmo.futsalService.reserve.model.CreateReservationModel;
import com.cosmo.futsalService.reserve.entity.ReservationDetails;
import com.cosmo.futsalService.reserve.entity.TimeSlot;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ReservationMapper {

    @Autowired
    protected BusinessHoursRepository businessHoursRepository;

    public ReservationDetails toEntity(CreateReservationModel createReservationModel) {
        ReservationDetails reservationDetails = new ReservationDetails();
        reservationDetails.setVendorCode(createReservationModel.getVendorCode());
        reservationDetails.setFutsal_uuid(createReservationModel.getFutsal_uuid());
        reservationDetails.setDate(createReservationModel.getDate());
        reservationDetails.setStartTime(createReservationModel.getStartTime());
        reservationDetails.setEndTime(createReservationModel.getEndTime());
        reservationDetails.setCode(UUID.randomUUID().toString());
        reservationDetails.setDay(createReservationModel.getDate().getDayOfWeek());
        return reservationDetails;
    }

    public boolean isWithinBusinessHours(ReservationDetails reservationDetails, BusinessHoursRepository businessHoursRepository) {
        DayOfWeek dayOfWeek = reservationDetails.getDate().getDayOfWeek();
        Optional<BusinessHours> businessHoursOpt = businessHoursRepository.findByVendorCode(reservationDetails.getVendorCode())
                .stream()
                .filter(bh -> !bh.isClosed() && bh.getDay() == dayOfWeek)
                .findFirst();

        if (businessHoursOpt.isEmpty()) {
            return false;
        }

        BusinessHours businessHours = businessHoursOpt.get();
        return !reservationDetails.getStartTime().isBefore(businessHours.getStartTime()) &&
                !reservationDetails.getEndTime().isAfter(businessHours.getEndTime());
    }

    public List<TimeSlot> calculateAvailableSlots(LocalTime openingTime, LocalTime closingTime, List<ReservationDetails> reservations) {
        List<TimeSlot> availableSlots = new ArrayList<>();
        LocalTime startTime = openingTime;

        for (ReservationDetails reservation : reservations) {
            if (startTime.isBefore(reservation.getStartTime())) {
                availableSlots.add(new TimeSlot(startTime, reservation.getStartTime()));
            }
            startTime = reservation.getEndTime();
        }

        if (startTime.isBefore(closingTime)) {
            availableSlots.add(new TimeSlot(startTime, closingTime));
        }

        return availableSlots;
    }
}
