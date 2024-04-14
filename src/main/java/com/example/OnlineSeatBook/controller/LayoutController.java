package com.example.OnlineSeatBook.controller;


import com.example.OnlineSeatBook.dto.SeatDTO;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/layout")
@CrossOrigin
public class LayoutController {

    @Autowired
    LayoutService service;

    @GetMapping("/{floorId}")
    public ArrayList<Seat> getSeatMap(@PathVariable Long floorId) {
        return service.getAllSeats(floorId);
    }

    @GetMapping("/map/floor/{floorId}/date/{date}")
    public List<List<SeatDTO>> getSectionMap(@PathVariable("floorId") Long floorId,@PathVariable("date") String dateString) {
        if (dateString == null || floorId==null) {
            return service.getDefaultMap();
        }
        LocalDate date = LocalDate.parse(dateString);
        return service.getFloorMap(floorId, date);
    }
}
