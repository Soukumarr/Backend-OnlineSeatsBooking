package com.example.OnlineSeatBook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "swap_request")
public class SwapRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   @OneToOne
    @JoinColumn(name = "user_id_1")
    private User user1;
    @OneToOne
    @JoinColumn(name = "user_id_2")
    private User user2;
    @ManyToOne
    @JoinColumn(name = "seat_id_1")
    private Seat seat1;
    @ManyToOne
    @JoinColumn(name = "seat_id_2")
    private Seat seat2;
    private boolean isApproved;

    // Getters, setters, and constructors


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Seat getSeat1() {
        return seat1;
    }

    public void setSeat1(Seat seat1) {
        this.seat1 = seat1;
    }

    public Seat getSeat2() {
        return seat2;
    }

    public void setSeat2(Seat seat2) {
        this.seat2 = seat2;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
