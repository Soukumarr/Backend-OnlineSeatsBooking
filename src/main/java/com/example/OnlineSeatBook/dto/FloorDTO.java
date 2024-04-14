package com.example.OnlineSeatBook.dto;

import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Seat;

public class FloorDTO {

    private int number;

    private int id;

    private String label;

    public static FloorDTO convertToDTO(Floor floor) {
        FloorDTO floorDTO = new FloorDTO();
        floorDTO.setLabel("Floor " + floor.getFloorNumber());
        floorDTO.setNumber(floor.getFloorNumber());
        floorDTO.setId(Math.toIntExact(floor.getId()));
        return floorDTO;
    }

    public FloorDTO() {}

    public FloorDTO(int number, int id, String label) {
        this.number = number;
        this.id = id;
        this.label = label;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
