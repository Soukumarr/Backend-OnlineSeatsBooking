package com.example.OnlineSeatBook.repository;

import com.example.OnlineSeatBook.model.Floor;
import com.example.OnlineSeatBook.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    @Query("SELECT f FROM Floor f WHERE f.office.id = :officeId")
    List<Floor> findByOfficeId(Long officeId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Floor f WHERE f.office.id = :officeId")
    void deleteByOfficeId(Long officeId);
}
