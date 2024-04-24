package com.example.OnlineSeatBook.controller;

import com.example.OnlineSeatBook.dto.FloorDTO;
import com.example.OnlineSeatBook.dto.OfficeDTO;
import com.example.OnlineSeatBook.exception.ResourceNotFoundException;
import com.example.OnlineSeatBook.model.Office;
import com.example.OnlineSeatBook.repository.OfficeRepository;
import com.example.OnlineSeatBook.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping
    public List<OfficeDTO> getAllOffices(@RequestHeader("Authorization") String token){
        List<Office> offices = officeRepository.findAll();
        System.out.println(offices); // This will print the list of offices to the console
        return offices.stream().map(
                (OfficeDTO::convertToEntity)
        ).toList();
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin("http://localhost:3000")
    @PostMapping
    public Office createOffice(@RequestBody Office office) {
        return officeRepository.save(office);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin("http://localhost:3000")
    @DeleteMapping("/{id}")
    public void deleteOffice(@PathVariable int id) {
        officeRepository.deleteById(id);
    }
    @CrossOrigin
//    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/{officeId}/getFloors")
    public List<FloorDTO> getAllFloors(@PathVariable int officeId) {
        return officeService.getAllFloors(officeId);
    }
}