package com.example.OnlineSeatBook.service;


import com.example.OnlineSeatBook.dto.BookingDTO;
import com.example.OnlineSeatBook.model.Booking;
import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.model.User;
import com.example.OnlineSeatBook.repository.BookingRepository;
import com.example.OnlineSeatBook.repository.FloorRepository;
import com.example.OnlineSeatBook.repository.SeatRepository;
import com.example.OnlineSeatBook.repository.UserRepository;
import com.example.OnlineSeatBook.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.OnlineSeatBook.dto.BookingDTO.convertToDTO;
import static com.example.OnlineSeatBook.dto.BookingDTO.convertToEntity;

@Service
public class BookingService {

    @Autowired
    FloorRepository floorRepository;

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


    public List<Map<String, Object>> getAllBookingDetails() {
        List<Booking> bookings = bookingRepository.findAll();
        List<Map<String, Object>> allBookingDetails = new ArrayList<>();

        for (Booking booking : bookings) {

            Long floorId = booking.getSeat().getFloor();
            Floor floor = floorRepository.findById(floorId).orElse(null);
            Map<String, Object> details = new HashMap<>();
            details.put("bookingId", booking.getId());
            details.put("startTime", booking.getStartTime());
            details.put("endTime", booking.getEndTime());
            details.put("seatId", booking.getSeat().getId());
            details.put("officename", floor.getOffice().getName());
            details.put("location", floor.getOffice().getLocation());
            details.put("username", booking.getUser().getName());

            details.put("status", booking.getStatus());
            details.put(("status"), booking.getStatus());
            allBookingDetails.add(details);
        }
        return allBookingDetails;
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

    public String updateBookingsOnExpiring() {
//        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
        LocalDateTime dateTime = LocalDateTime.now();
        String today = dateTime.format(dateformatter) + ":" + dateTime.format(timeFormatter);
        System.out.println("Today is " + today);

//        get all bookings where time date is today

        List<Booking> bookingList = bookingRepository.findAllByDate(LocalDate.now()).stream().map(
                (booking) ->{
//                        System.out.println("BookingID: " + booking.getId() + "  End Time : " + booking.getEndTime().format(timeFormatter));
//                        String currentHour = LocalDateTime.now().format(timeFormatter).substring(0,2);
                    String currentHour = "15";
                        String endHour = booking.getEndTime().format(timeFormatter).substring(0,2);
                        if (  Integer.parseInt(currentHour) > Integer.parseInt(endHour) && booking.getStatus()!=Status.EXPIRED){
                            booking.setStatus(Status.EXPIRED);
                            bookingRepository.save(booking);
                            System.out.println("Updated an expired Booking");
                        }
                    return booking;
                }
        ).toList();
        return null;
    }


    public boolean cancelRequest(Long id) {

        Booking booking = bookingRepository.findById(id).orElse(null);

        if ( !booking.equals(null)){
            booking.setStatus(Status.CANCEL);
            bookingRepository.save(booking);
            return true;
        }else {
            return false;
        }
    }
}
