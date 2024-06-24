package com.example.job_search_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/client")
@Validated
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<String> registerClient(@Valid @RequestBody Client client) {
        Client registeredClient = clientService.registerClient(client);
        return ResponseEntity.ok(registeredClient.getApiKey());
    }
}