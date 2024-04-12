package com.example.OnlineSeatBook.repository;

import com.example.OnlineSeatBook.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBySeatIdAndDate(Long seatId, LocalDate date);

//    TODO: get Bookings by seatId & Date

}
