package com.example.OnlineSeatBook.service;


import com.example.OnlineSeatBook.dto.BookingDTO;
import com.example.OnlineSeatBook.model.Booking;
import com.example.OnlineSeatBook.repository.BookingRepository;
import com.example.OnlineSeatBook.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    BookingRepository bookingRepository;

    public List<BookingDTO> getHistory(Long userId) {
        List<Booking> bookingList =  bookingRepository.findAllByUserId(userId);
        List<BookingDTO> dtoList = bookingList.stream()
                .filter(booking -> booking.getStatus() == Status.EXPIRED)
                .map(
                (booking )->{
                    return BookingDTO.convertToDTO(booking);
                }
        ).toList();
//        System.out.println(bookingList);
        return dtoList;
    }
}
