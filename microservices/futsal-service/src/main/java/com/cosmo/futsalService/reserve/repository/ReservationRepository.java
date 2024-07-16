package com.cosmo.futsalService.reserve.repository;

import com.cosmo.futsalService.reserve.entity.ReservationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface ReservationRepository extends JpaRepository<ReservationDetails, Long> {
    List<ReservationDetails> findByVendorCodeAndDateOrderByStartTimeAsc(String vendorCode, LocalDate date);

    @Query("SELECT r FROM ReservationDetails r WHERE r.vendorCode = :vendorCode AND r.futsal_uuid = :futsalUuid AND r.date = :date AND " +
            "((r.startTime < :endTime AND r.endTime > :startTime) OR (r.startTime < :startTime AND r.endTime > :endTime))")
    List<ReservationDetails> findOverlappingReservations(@Param("vendorCode") String vendorCode,
                                                         @Param("futsalUuid") String futsalUuid,
                                                         @Param("date") LocalDate date,
                                                         @Param("startTime") LocalTime startTime,
                                                         @Param("endTime") LocalTime endTime);
}
