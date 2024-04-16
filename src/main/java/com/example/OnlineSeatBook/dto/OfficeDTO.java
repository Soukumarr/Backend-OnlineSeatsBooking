package com.example.OnlineSeatBook.dto;
import com.example.OnlineSeatBook.model.Booking;
import com.example.OnlineSeatBook.model.Office;
import com.example.OnlineSeatBook.model.Seat;
import com.example.OnlineSeatBook.model.User;
import java.util.List;
public class OfficeDTO {
    private Long id;
    private String name;
    private String location;
    private int floorCount;
    private int totalSeatCount;
    private int availableSeatCount;
    public OfficeDTO() {
    }
    public static OfficeDTO convertToEntity(Office office) {
        OfficeDTO officeDTO = new OfficeDTO();
        officeDTO.setAvailableSeatCount(office.getAvailableSeatCount());
        officeDTO.setFloorCount(office.getFloorCount());
        officeDTO.setId(office.getId());
        officeDTO.setLocation(office.getLocation());
        officeDTO.setName(office.getName());
        return officeDTO;
    }
    public OfficeDTO(Long id, String name, String location, int floorCount, int totalSeatCount, int availableSeatCount) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.floorCount = floorCount;
        this.totalSeatCount = totalSeatCount;
        this.availableSeatCount = availableSeatCount;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getFloorCount() {
        return floorCount;
    }
    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }
    public int getTotalSeatCount() {
        return totalSeatCount;
    }
    public void setTotalSeatCount(int totalSeatCount) {
        this.totalSeatCount = totalSeatCount;
    }
    public int getAvailableSeatCount() {
        return availableSeatCount;
    }
    public void setAvailableSeatCount(int availableSeatCount) {
        this.availableSeatCount = availableSeatCount;
    }
}
