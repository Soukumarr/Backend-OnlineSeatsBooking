package com.example.OnlineSeatBook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "floors")
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int floorNumber;
    private int seatCapacity;

    @ManyToOne
    private Office office;

    @OneToMany(mappedBy = "floor")
//    @JsonIgnoreProperties("seats")
    private List<Seat> seats;

    // Getters, setters, and constructors
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Floor(Office office, int floorNumber, int seatCapacity) {
        this.office = office;
        this.floorNumber = floorNumber;
        this.seatCapacity = seatCapacity;
        this.seats = new ArrayList<>();
    }

    public Floor() {
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", floorNumber=" + floorNumber +
                ", seatCapacity=" + seatCapacity +
                ", office=" + office +
                ", seats=" + seats +
                '}';
    }
}