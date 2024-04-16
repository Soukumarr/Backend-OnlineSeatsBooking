package com.example.OnlineSeatBook.model;


import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import java.util.Set;

@Entity
@Table (name = "office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private int floorCount;
    private int totalSeatCount;
    private int availableSeatCount;

    public Office(Long officeId) {
        this.id = officeId;
    }

    public Office() {

    }

    public Office(Long id, String name, String location, int floorCount, int totalSeatCount, Integer availableSeatCount, List<Floor> floors) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.floorCount = floorCount;
        this.totalSeatCount = totalSeatCount;
        this.availableSeatCount = availableSeatCount;
        this.floors = floors;
    }


//    public Set<Floor> getFloors() {
//        return floors;
//    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    //    Removing all the floors in this office if the office gets deleted
    @OneToMany(mappedBy = "office", cascade = CascadeType.REMOVE)
    private List<Floor> floors;

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

    public Integer getAvailableSeatCount() {
        return availableSeatCount;
    }
//make it default null

    public void setAvailableSeatCount(Integer availableSeatCount) {
        if (availableSeatCount == null) {
            this.availableSeatCount = 0;
        } else {
            this.availableSeatCount = availableSeatCount;
        }
    }

    public List<Floor> getFloors() {
        return this.floors;
    }
}