package com.example.job_search_app;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findByTitleContainingAndLocationContaining(String title, String location);
}