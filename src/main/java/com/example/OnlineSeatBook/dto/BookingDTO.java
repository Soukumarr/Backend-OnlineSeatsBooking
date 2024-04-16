package com.example.OnlineSeatBook.dto;

import com.example.OnlineSeatBook.model.Booking;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.model.User;
import com.example.OnlineSeatBook.repository.UserRepository;
import com.example.OnlineSeatBook.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class BookingDTO {

    private Long userId;
    private Long seatId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate date;
    private Status status;

    public static BookingDTO convertToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setUserId((long) booking.getUser().getId());
        bookingDTO.setSeatId((long) booking.getSeat().getId());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setDate(booking.getDate());
        bookingDTO.setStatus(booking.getStatus());
        return bookingDTO;
    }

    public static Booking convertToEntity(BookingDTO bookingDTO, Seat seat, User user) {
        Booking booking = new Booking();

        System.out.println("Start Time : " + bookingDTO.getEndTime());
        // Set other properties as needed
        booking.setDate(bookingDTO.getDate());
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());
        booking.setStatus(bookingDTO.getStatus());
        booking.setSeat(seat);
        booking.setUser(user);
        return booking;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "userId=" + userId +
                ", seatId=" + seatId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}