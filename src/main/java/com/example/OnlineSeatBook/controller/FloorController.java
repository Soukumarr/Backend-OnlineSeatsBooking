package com.example.OnlineSeatBook.controller;

import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/floors")
public class FloorController {

    @Autowired
    private final FloorService floorService;

    @Autowired
    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    // Create Floor (POST request)
    @PostMapping
    public Floor createFloor(@RequestBody Floor floor) {
        return floorService.saveFloor(floor);
    }

    // Read All Floors (GET request)
    @GetMapping
    public Iterable<Floor> getAllFloors() {
        return floorService.getAllFloors(); // Assuming a typo, should be getAllFloors()
    }

    // Read Floor by Id (GET request with path variable)
    @GetMapping("/{id}")
    public Optional<Floor> getFloorById(@PathVariable Long id) {
        return floorService.getFloorById(id);
    }

    // Update Floor (PUT request)
    @PutMapping("/{id}")
    public Floor updateFloor(@PathVariable Long id, @RequestBody Floor floor) {
        floor.setId(id); // Ensure ID from path matches the Floor object
        return floorService.updateFloor(floor);
    }

    // Delete Floor (DELETE request)
    @DeleteMapping("/{id}")
    public void deleteFloor(@PathVariable Long id) {
        floorService.deleteFloor(id);
    }


}
