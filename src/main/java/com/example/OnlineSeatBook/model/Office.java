package com.example.OnlineSeatBook.model;


import jakarta.persistence.*;

@Entity
@Table (name = "office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    private int floorCount;
    private int totalSeatCount;


    private Integer availableSeatCount= null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Integer getAvailableSeatCount() {
        return availableSeatCount;
    }
//make it default null

    public void setAvailableSeatCount(Integer availableSeatCount) {
        if (availableSeatCount == null) {
            this.availableSeatCount = null;
        } else {
            this.availableSeatCount = availableSeatCount;
        }
    }
}
