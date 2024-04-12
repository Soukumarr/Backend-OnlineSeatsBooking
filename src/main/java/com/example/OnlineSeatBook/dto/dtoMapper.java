package com.example.OnlineSeatBook.dto;

import com.example.OnlineSeatBook.model.Seat;

public class dtoMapper {

    public static SeatDTO mapSeatToDto(Seat seat) {
        SeatDTO dtoObj = new SeatDTO();
        dtoObj.setId(seat.getId());
        dtoObj.setAvailable(seat.isAvailable());
        dtoObj.setFloor(seat.getFloor());
        dtoObj.setSeatIndex(seat.getSeatIndex());
        dtoObj.setSection(seat.getSection());
        return dtoObj;
    }

}
