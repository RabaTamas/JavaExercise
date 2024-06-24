package com.example.job_search_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository jobPositionRepository;

    public Position createJobPosition(Position jobPosition, Client client) {
        jobPosition.setClient(client);
        return jobPositionRepository.save(jobPosition);
    }

    public List<Position> searchJobPositions(String keyword, String location) {
        return jobPositionRepository.findByTitleContainingAndLocationContaining(keyword, location);
    }

    public Position getJobPositionById(Long id) {
        return jobPositionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Position not found"));
    }
}