package com.example.OnlineSeatBook.repository;

import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {


    ArrayList<Seat> findAllByFloor(Floor floor);
}
