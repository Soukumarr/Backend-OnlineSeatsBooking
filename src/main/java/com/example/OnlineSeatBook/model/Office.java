package com.example.OnlineSeatBook.model;


import jakarta.persistence.*;

import java.util.Set;

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


    public Office(int id, String name, String location, int floorCount, int totalSeatCount, Integer availableSeatCount, Set<Floor> floors) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.floorCount = floorCount;
        this.totalSeatCount = totalSeatCount;
        this.availableSeatCount = availableSeatCount;
        this.floors = floors;
    }

    public Set<Floor> getFloors() {
        return floors;
    }

    public void setFloors(Set<Floor> floors) {
        this.floors = floors;
    }

//    Removing all the floors in this office if the office gets deleted
    @OneToMany(mappedBy = "office", cascade = CascadeType.REMOVE)
    private Set<Floor> floors;

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
