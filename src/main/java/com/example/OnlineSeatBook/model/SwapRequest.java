package com.example.OnlineSeatBook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "swap_request")
public class SwapRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public SwapRequest(User user1, User user2, Seat seat1, Seat seat2, boolean isApproved) {
        this.user1 = user1;
        this.user2 = user2;
        this.seat1 = seat1;
        this.seat2 = seat2;
        this.isApproved = isApproved;
    }
}