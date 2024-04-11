package com.example.OnlineSeatBook.controller;

import com.example.OnlineSeatBook.exception.ResourceNotFoundException;
import com.example.OnlineSeatBook.model.Office;
import com.example.OnlineSeatBook.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {
    @Autowired
    private OfficeRepository officeRepository;
    @CrossOrigin("http://localhost:3000")
    @GetMapping
    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }
    @CrossOrigin("http://localhost:3000")
    @PostMapping
    public Office createOffice(@RequestBody Office office) {
        return officeRepository.save(office);
    }

    @CrossOrigin("http://localhost:3000")
    @PutMapping("/{id}")
    public Office updateOffice(@PathVariable int id, @RequestBody Office updatedOffice) {
        return officeRepository.findById(id)
                .map(office -> {
                    office.setName(updatedOffice.getName());
                    office.setLocation(updatedOffice.getLocation());
                    office.setFloorCount(updatedOffice.getFloorCount());
//                    office.setTotalSeatCount(updatedOffice.getTotalSeatCount());
//                    office.setAvailableSeatCount(updatedOffice.getAvailableSeatCount());
                    return officeRepository.save(office);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Office not found with id " + id));
    }
    @CrossOrigin("http://localhost:3000")
    @DeleteMapping("/{id}")
    public void deleteOffice(@PathVariable int id) {
        officeRepository.deleteById(id);
    }
}
