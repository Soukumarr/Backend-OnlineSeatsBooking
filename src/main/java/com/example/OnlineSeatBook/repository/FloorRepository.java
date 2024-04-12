package com.example.OnlineSeatBook.repository;

import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    @Query("SELECT f FROM Floor f WHERE f.office.id = :officeId")
    List<Floor> findByOfficeId(Long officeId);

}
