package com.example.OnlineSeatBook.repository;

import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
}
