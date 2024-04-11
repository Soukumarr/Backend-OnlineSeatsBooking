package com.example.OnlineSeatBook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Floor floor;
    private int seatIndex;
    private int section;
    private boolean isAvailable;

    // Getters, setters, and constructors
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public int getSeatIndex() {
        return seatIndex;
    }

    public void setSeatIndex(int seatIndex) {
        this.seatIndex = seatIndex;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public Seat(int seatNumber, int section, boolean isAvailable) {
        this.seatIndex = seatNumber;
        this.isAvailable = isAvailable;
        this.section= section;
        this.floor = new Floor();
    }

    public Seat() {
    }
}