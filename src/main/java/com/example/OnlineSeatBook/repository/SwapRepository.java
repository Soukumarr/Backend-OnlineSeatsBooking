package com.example.OnlineSeatBook.repository;

import com.example.OnlineSeatBook.model.SwapRequest;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwapRepository extends JpaRepository<SwapRequest, Long> {
}
