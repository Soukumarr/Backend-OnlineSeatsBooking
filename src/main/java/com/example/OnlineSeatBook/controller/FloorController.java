package com.example.OnlineSeatBook.controller;

import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.lang.Integer.parseInt;


@RestController
@RequestMapping("api/floors")
public class FloorController {



    @Autowired
    private final FloorService floorService;



    @Autowired
    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }
    @CrossOrigin("http://localhost:3000")
    @PostMapping
    public Floor createFloor(@RequestBody Floor floor) {
        return floorService.saveFloor(floor);
    }

    // Read All Floors (GET request)
    @GetMapping
    @CrossOrigin("http://localhost:3000")
    public Iterable<Floor> getAllFloors() {
        return floorService.getAllFloors(); // Assuming a typo, should be getAllFloors()
    }

    // Read Floor by Id (GET request with path variable)
    @CrossOrigin("http://localhost:3000")
    @GetMapping("/{id}")
    public Optional<Floor> getFloorById(@PathVariable Long id) {
        return floorService.getFloorById(id);
    }

    // Update Floor (PUT request)
    @CrossOrigin("http://localhost:3000")
    @PutMapping("/{id}")
    public Floor updateFloor(@PathVariable Long id, @RequestBody Floor floor) {
        floor.setId(id); // Ensure ID from path matches the Floor object
        return floorService.updateFloor(floor);
    }

    @PutMapping("/office/{officeId}/floors/{floorCount}")
    public List<Floor> updateFloorCount(@PathVariable Long officeId, @PathVariable int floorCount) {
//        // if floorcount in string then convert it into int
//        floorCount = parseInt(String.valueOf(floorCount));
        List<Floor> floors = floorService.getFloorsByOfficeId(officeId);
        int currentCount = floors.size();


        // Sort floors by floor number in ascending order

        Collections.sort(floors, Comparator.comparing(Floor::getFloorNumber));

        // print floors in console
        System.out.println(floors);

        if (currentCount > floorCount) {
            // Delete extra floors
            for (int i = currentCount; i > floorCount; i--) {
                if(floors.get(i - 1).getOfficeId() == officeId) {
                    floorService.deleteFloor(floors.get(i - 1).getId());
                }
            }
        } else if (currentCount < floorCount) {
            // Create new floors
            for (int i = currentCount; i < floorCount; i++) {
                Floor newFloor = new Floor();
                newFloor.setOfficeId(officeId);
                newFloor.setFloorNumber(i + 1);
                floorService.saveFloor(newFloor);
            }
        }

        return floorService.getFloorsByOfficeId(officeId);
    }
    // Delete Floor (DELETE request)
    @CrossOrigin("http://localhost:3000")
    @DeleteMapping("/{id}")
    public void deleteFloor(@PathVariable Long id) {
        floorService.deleteFloor(id);
    }

    @DeleteMapping("/office/{officeId}")
    public void deleteFloorByOfficeId(@PathVariable Long officeId) {
        floorService.deleteFloorByOfficeId(officeId);
    }


}
