package com.example.job_search_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/position")
@Validated
public class PositionController {

    @Autowired
    private PositionService jobPositionService;

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<String> createJobPosition(@RequestHeader("API-Key") String apiKey,
                                                    @Valid @RequestBody Position jobPosition) {
        Client client = clientService.validateApiKey(apiKey);
        Position createdPosition = jobPositionService.createJobPosition(jobPosition, client);
        return ResponseEntity.ok("/position/" + createdPosition.getId());
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchJobPositions(@RequestHeader("API-Key") String apiKey,
                                                           @RequestParam @Size(max = 50) String keyword,
                                                           @RequestParam @Size(max = 50) String location) {
        clientService.validateApiKey(apiKey);
        List<Position> jobPositions = jobPositionService.searchJobPositions(keyword, location);
        List<String> urls = jobPositions.stream()
                .map(job -> "/position/" + job.getId())
                .collect(Collectors.toList());
        return ResponseEntity.ok(urls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getJobPosition(@RequestHeader("API-Key") String apiKey, @PathVariable Long id) {
        clientService.validateApiKey(apiKey);
        Position jobPosition = jobPositionService.getJobPositionById(id);
        return ResponseEntity.ok(jobPosition);
    }
}