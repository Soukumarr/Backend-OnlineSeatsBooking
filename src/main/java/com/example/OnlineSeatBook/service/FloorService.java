package com.example.OnlineSeatBook.service;

import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FloorService {

    @Autowired
    private final FloorRepository floorRepository;

    public FloorService(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }






    // Create (Save) Floor
    public Floor saveFloor(Floor floor) {
        return floorRepository.save(floor);
    }

    // Read (Find All) Floors
    public Iterable<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    // Read (Find By Id) Floor
    public Optional<Floor> getFloorById(Long id) {
        return floorRepository.findById(id);
    }

    // Update Floor
    public Floor updateFloor(Floor floor) {
        return floorRepository.save(floor);
    }
    public List<Floor> getFloorsByOfficeId(Long officeId) {
        return floorRepository.findByOfficeId(officeId);
    }
    // Delete Floor
    public void deleteFloor(Long id) {
        floorRepository.deleteById(id);
    }

    public void deleteFloorByOfficeId(Long officeId) {
        floorRepository.deleteByOfficeId(officeId);
    }
}
// deleteFloor by officeId
