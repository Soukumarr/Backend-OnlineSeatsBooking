package com.example.OnlineSeatBook.controller;

import com.example.OnlineSeatBook.dto.BookingDTO;
import com.example.OnlineSeatBook.model.Booking;
import com.example.OnlineSeatBook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")

@RequestMapping("/api/booking/")

public class BookingController {
    @Autowired
    private BookingService bookingService;

    // Create a new booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO booking) {
        System.out.println(booking.toString());
        Booking createdBooking = bookingService.createBooking(booking);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    // Get all bookings
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }


    @GetMapping("/dashboard")
    public List<Map<String, Object>> getAllBookingDetails() {
        return bookingService.getAllBookingDetails();
    }

    // Get a booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        BookingDTO booking = bookingService.getBookingById(id);
        if (booking != null) {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a booking
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody BookingDTO booking) {
        Booking updatedBooking = bookingService.updateBooking(id, booking);
        if (updatedBooking != null) {
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a booking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        boolean deleted = bookingService.deleteBooking(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelBookingRequest(@PathVariable Long id) {
        boolean deleted = bookingService.cancelRequest(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all bookings by date and seat ID
    @GetMapping("/date/{date}/seat/{seatId}")
    public ResponseEntity<BookingDTO> getBookingsByDateAndSeatId(@PathVariable("date") String dateString, @PathVariable("seatId") int seatId) {
        LocalDate date = LocalDate.parse(dateString);
        BookingDTO bookings = bookingService.getBookingsBySeatIdAndDate(seatId, date);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/all/date/{date}/seat/{seatId}")
    public ResponseEntity<List<BookingDTO>> getAllBookingsBySeatIdandDate (@PathVariable("date") String dateString, @PathVariable("seatId") Integer seatId) {
        LocalDate date = LocalDate.parse(dateString);
        List<BookingDTO> bookings = bookingService.getAllBookingsBySeatIdAndDate(seatId, date);
        return new ResponseEntity<>(bookings, HttpStatus.OK);

    }

}
