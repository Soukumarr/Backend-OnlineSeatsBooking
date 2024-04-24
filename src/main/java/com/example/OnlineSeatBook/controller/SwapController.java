package com.example.OnlineSeatBook.controller;


import com.example.OnlineSeatBook.dto.BookingDTO;
import com.example.OnlineSeatBook.model.SwapRequest;
import com.example.OnlineSeatBook.service.SwapService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")

@RequestMapping("/api/swap/")

public class SwapController {


    @Autowired
    SwapService swapService;

    @GetMapping("/booking/{bId}/seat/{seatId}")
    public ResponseEntity<SwapRequest> makeSwap(@PathVariable("bId") Long bookingId, @PathVariable("seatId") Long seatId){


        return  new ResponseEntity<>(swapService.makeSwapRequest( bookingId, seatId), HttpStatus.OK);

    }
}
