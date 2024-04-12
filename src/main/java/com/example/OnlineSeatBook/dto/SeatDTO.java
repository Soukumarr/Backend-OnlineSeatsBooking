package com.example.OnlineSeatBook.dto;

import com.example.OnlineSeatBook.model.Floor;

public class SeatDTO{

    private int id;
    private Long floorId;
    private int seatIndex;
    private int section;
    private boolean isAvailable;

    public SeatDTO(int id, Long floor, int seatIndex, int section, boolean isAvailable) {
        this.id = id;
        this.floorId = floor;
        this.seatIndex = seatIndex;
        this.section = section;
        this.isAvailable = isAvailable;
    }

    public SeatDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getFloor() {
        return floorId;
    }

    public void setFloor(Long floorId) {
        this.floorId = floorId;
    }

    public int getSeatIndex() {
        return seatIndex;
    }

    public void setSeatIndex(int seatIndex) {
        this.seatIndex = seatIndex;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
