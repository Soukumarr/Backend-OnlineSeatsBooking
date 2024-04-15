package com.example.OnlineSeatBook.controller;

import com.example.OnlineSeatBook.dto.SeatDTO;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seat/")
@CrossOrigin
public class SeatController {

    @Autowired
    SeatService seatService;


    @PostMapping("office/{officeId}")
    public SeatDTO addNewSeat(@RequestBody SeatDTO seatDTO,@PathVariable Integer officeId){
        System.out.println(officeId);
        return seatService.addSeat(seatDTO, officeId);
    }

}
