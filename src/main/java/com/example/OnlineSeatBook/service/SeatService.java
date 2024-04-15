package com.example.OnlineSeatBook.service;

import com.example.OnlineSeatBook.dto.FloorDTO;
import com.example.OnlineSeatBook.dto.SeatDTO;
import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.repository.FloorRepository;
import com.example.OnlineSeatBook.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;
    @Autowired
    OfficeService officeService;

    @Autowired
    FloorService floorService;

    public SeatDTO addSeat(SeatDTO seatDTO, Integer officeId) {
        Floor currentFloor = null;
        List<Floor> floors =  officeService.getAllFloorsObject(officeId);
        for ( Floor floor : floors){
            if (seatDTO.getFloorId() == floor.getFloorNumber()){
                currentFloor = floor;
            }
        }
        Seat  seat = SeatDTO.convertToEntity(seatDTO, currentFloor);
        return SeatDTO.convertToDTO(seatRepository.save(seat));
    }
}
