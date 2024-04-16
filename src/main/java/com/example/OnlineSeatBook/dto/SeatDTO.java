package com.example.OnlineSeatBook.dto;

import com.example.OnlineSeatBook.model.Booking;
import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.util.Status;

public class SeatDTO{

    private int id;
    private Long floorId;
    private int seatIndex;
    private int section;
    private boolean isAvailable;

    private Status status;

    private Long bookingId;


    public SeatDTO(int id, Long floorId, int seatIndex, int section, boolean isAvailable, Status status) {
        this.id = id;
        this.floorId = floorId;
        this.seatIndex = seatIndex;
        this.section = section;
        this.isAvailable = isAvailable;
        this.status = status;
    }

    public static SeatDTO convertToDTO(Seat seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setId(seat.getId());
        seatDTO.setFloorId(seat.getFloor());
        seatDTO.setSeatIndex(seat.getSeatIndex());
        seatDTO.setSection(seat.getSection());
        seatDTO.setAvailable(seat.isAvailable());
        return seatDTO;
    }


    public SeatDTO() {

    }

    public static Seat convertToEntity(SeatDTO seatDTO, Floor floor) {
        Seat seat = new Seat();
        seat.setSection(seatDTO.getSection());
        seat.setSeatIndex(seatDTO.getSeatIndex());
        seat.setAvailable(true);
        seat.setFloor(floor);
        return seat;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SeatDTO{" +
                "id=" + id +
                ", floorId=" + floorId +
                ", seatIndex=" + seatIndex +
                ", section=" + section +
                ", isAvailable=" + isAvailable +
                ", status=" + status +
                ", bookingId=" + bookingId +
                '}';
    }
}
