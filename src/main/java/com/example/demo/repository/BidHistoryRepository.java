package com.example.demo.repository;

import com.example.demo.entity.BidHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidHistoryRepository extends JpaRepository<BidHistory, Long> {
}
