package com.example.OnlineSeatBook.service;


import com.example.OnlineSeatBook.dto.BookingDTO;
import com.example.OnlineSeatBook.model.Booking;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.model.User;
import com.example.OnlineSeatBook.repository.BookingRepository;
import com.example.OnlineSeatBook.repository.SeatRepository;
import com.example.OnlineSeatBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.OnlineSeatBook.dto.BookingDTO.convertToDTO;
import static com.example.OnlineSeatBook.dto.BookingDTO.convertToEntity;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeatRepository seatRepository;

    public Booking createBooking(BookingDTO bookingDTO) {

        Seat seat = seatRepository.findById(bookingDTO.getSeatId()).get();
        User user = userRepository.findById(Math.toIntExact(bookingDTO.getUserId())).get();
        Booking booking = convertToEntity(bookingDTO, seat, user);

        return bookingRepository.save(booking);
    }

    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(BookingDTO::convertToDTO).collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        return BookingDTO.convertToDTO(booking);
    }

    public Booking updateBooking(Long id, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        Booking updatedBooking = convertToEntity(bookingDTO, null, null); // TODO: add user and seat
        existingBooking.setUser(updatedBooking.getUser());
        existingBooking.setSeat(updatedBooking.getSeat());
        existingBooking.setStartTime(updatedBooking.getStartTime());
        existingBooking.setEndTime(updatedBooking.getEndTime());
        existingBooking.setStatus(updatedBooking.getStatus());
        existingBooking.setDate(updatedBooking.getDate());
        return bookingRepository.save(existingBooking);
    }

    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Booking not found");
    }

    public BookingDTO getBookingsBySeatIdAndDate(int seatId, LocalDate date) {
        Booking booking = bookingRepository.findBySeatIdAndDate(Long.valueOf(seatId), date);
        if (booking == null) {
            return null;
        }
        return convertToDTO(booking);
    }


}
