package com.example.OnlineSeatBook.service;


import com.example.OnlineSeatBook.dto.BookingDTO;
import com.example.OnlineSeatBook.model.Booking;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.model.SwapRequest;
import com.example.OnlineSeatBook.model.User;
import com.example.OnlineSeatBook.repository.BookingRepository;
import com.example.OnlineSeatBook.repository.SeatRepository;
import com.example.OnlineSeatBook.repository.SwapRepository;
import com.example.OnlineSeatBook.repository.UserRepository;
import com.example.OnlineSeatBook.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.OnlineSeatBook.dto.BookingDTO.convertToEntity;

@Service
public class SwapService {


    @Autowired
    BookingService bookingService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SwapRepository swapRepository;

    public SwapRequest makeSwapRequest(Long bookingId, Long seatId) {

        SwapRequest request = null;
//        TODO: get Booking from bookingId

        Booking booking = bookingRepository.findById(bookingId).orElse(null);

//       Get all bookings for given Seat and date

        List<BookingDTO> presentBookingsList = bookingService.getAllBookingsBySeatIdAndDate(Math.toIntExact(seatId),booking.getDate());

//        TODO: if no bookings are present swap the seat
        if (presentBookingsList == null){
            Seat seat = seatRepository.findById(seatId).orElse(null);
            booking.setSeat(seat);
            bookingRepository.save(booking);
        }else{
//            check if the durations of the two bookings collide if they do then crate a swap request else swap the seat

            for (BookingDTO presentBooking : presentBookingsList) {
                if (
                        ( booking.getStartTime().isBefore(presentBooking.getStartTime()) &&
                                booking.getEndTime().isBefore(presentBooking.getStartTime()))
                        ||

                                ( booking.getStartTime().isAfter(presentBooking.getEndTime()) &&
                                        booking.getEndTime().isAfter(presentBooking.getEndTime()))
                ){
//                     This means there is no booking duration conflict
                    Seat seat = seatRepository.findById(seatId).orElse(null);
                    booking.setSeat(seat);
                    bookingRepository.save(booking);
                }else {

                    User user1 = booking.getUser();
                    User user2 = userRepository.findById(presentBooking.getUserId()).get();
                    Seat seat1 = booking.getSeat();
                    Seat seat2 = seatRepository.findById(presentBooking.getSeatId()).get();
                    SwapRequest swapRequest = new SwapRequest(user1, user2, seat1, seat2, false);
                    request = swapRepository.save(swapRequest);
                    booking.setStatus(Status.SWAP);
                    Booking presentBookingEntity = bookingRepository.findById(presentBooking.getId()).get();
                    presentBookingEntity.setStatus(Status.SWAP);
                    bookingRepository.save(presentBookingEntity);
                    bookingRepository.save(booking);
                    System.out.println("Created Swap Request!!");
                }
            }
        }
//        TODO: if bookings are present create a swap request
        return request;

    }
}
