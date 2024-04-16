package com.example.OnlineSeatBook.controller;

import com.example.OnlineSeatBook.dto.SeatDTO;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat/")
@CrossOrigin
public class SeatController {

    @Autowired
    SeatService seatService;

    @GetMapping
    public List<SeatDTO> getAllSeats(){
        return seatService.getAll();
    }


    @PostMapping("office/{officeId}")
    public SeatDTO addNewSeat(@RequestBody SeatDTO seatDTO,@PathVariable Integer officeId){
        System.out.println(officeId);
        return seatService.addSeat(seatDTO, officeId);
    }

    @DeleteMapping("{seatId}")
    public ResponseEntity<Boolean> deleteSeat(@PathVariable Long seatId){
        seatService.deleteSeat(seatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
