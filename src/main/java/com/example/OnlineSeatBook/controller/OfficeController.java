package com.example.OnlineSeatBook.controller;

import com.example.OnlineSeatBook.dto.FloorDTO;
import com.example.OnlineSeatBook.model.Office;
import com.example.OnlineSeatBook.repository.OfficeRepository;
import com.example.OnlineSeatBook.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offices")

public class OfficeController {
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    OfficeService officeService;
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
    @DeleteMapping("/{id}")
    public void deleteOffice(@PathVariable int id) {
        officeRepository.deleteById(id);
    }

    @CrossOrigin
    @GetMapping("/{officeId}/getFloors")
    public List<FloorDTO> getAllFloors(@PathVariable int officeId) {
        return officeService.getAllFloors(officeId);
    }
}
