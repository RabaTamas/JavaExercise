package com.example.job_search_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolationException;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    public Client registerClient(Client client) {
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new ConstraintViolationException("Email already exists", null);
        }
        return clientRepository.save(client);
    }

    public Client validateApiKey(String apiKey) {
        return clientRepository.findByApiKey(apiKey)
                .orElseThrow(() -> new IllegalArgumentException("Invalid API Key"));
    }
}