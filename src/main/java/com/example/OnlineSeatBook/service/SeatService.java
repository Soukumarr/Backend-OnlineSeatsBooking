package com.example.OnlineSeatBook.service;

import com.example.OnlineSeatBook.dto.SeatDTO;
import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteSeat(Long seatId) {
        seatRepository.deleteById(seatId);
    }

    public List<SeatDTO> getAll() {
        return seatRepository.findAll().stream().map(
                SeatDTO::convertToDTO
        ).collect(Collectors.toList());
    }
}
