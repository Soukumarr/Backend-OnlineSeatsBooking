package com.example.OnlineSeatBook.controller;


import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return service.getSeatMap(floorId);
    }

    @GetMapping("/map/{floorId}")
    public List<List<Seat>> getSectionMap(@PathVariable Long floorId) {
        return service.getSectionMap(floorId);
    }
}
