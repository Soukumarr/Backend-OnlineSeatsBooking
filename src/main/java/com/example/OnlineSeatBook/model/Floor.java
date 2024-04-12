package com.example.OnlineSeatBook.model;

import jakarta.persistence.*;

import java.util.HashSet;
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
    @JoinColumn(name = "office_id")
    private Office office;

    @OneToMany(mappedBy = "floor")
    private Set<Seat> seats;

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

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public Floor(Office office, int floorNumber, int seatCapacity) {
        this.office = office;
        this.floorNumber = floorNumber;
        this.seatCapacity = seatCapacity;
        this.seats = new HashSet<>();
    }


    public Floor() {
    }

// setofficeid
    public void setOfficeId(Long officeId) {
        this.office = new Office(officeId);
    }
    // getofficeid
    public Long getOfficeId() {
        return office.getId();
    }

}