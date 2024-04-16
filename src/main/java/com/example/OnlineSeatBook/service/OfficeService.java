package com.example.OnlineSeatBook.service;

import com.example.OnlineSeatBook.dto.FloorDTO;
import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Office;
import com.example.OnlineSeatBook.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeService {


    @Autowired
    OfficeRepository officeRepository;


    public List<FloorDTO> getAllFloors(int officeId) {
        Office office = officeRepository.findById(officeId).get();


        return officeRepository.findById(officeId).get().getFloors().stream().map(
                (FloorDTO::convertToDTO)
        ).collect(Collectors.toList());
    }

    public List<Floor> getAllFloorsObject(int officeId) {
        Office office = officeRepository.findById(officeId).get();
        return officeRepository.findById(officeId).get().getFloors().stream().toList();
    }


}
