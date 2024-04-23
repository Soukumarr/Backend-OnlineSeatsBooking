package com.example.OnlineSeatBook.repository;

import com.example.OnlineSeatBook.model.Booking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findBySeatIdAndDate(Long seatId, LocalDate date);

    @Query("SELECT b FROM booking b WHERE b.date  = :#{#date} ")
    List<Booking> findAllByDate(@Param("date") LocalDate date);

    @Query("SELECT b FROM booking b WHERE b.date  = :#{#date} and  b.seat.id = :#{#seatId}")
    List<Booking> findAllBySeatAndDate(@Param("seatId")Integer seatId, @Param("date")LocalDate date);

//    TODO: get Bookings by seatId & Date

}
