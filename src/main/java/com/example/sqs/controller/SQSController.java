package com.example.sqs.controller;

import com.example.sqs.service.impl.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sqs")
public class SQSController {

    private final ClientService clientService;
    
    public SQSController(ClientService clientService) {
        this.clientService = clientService;
    }
    
    @GetMapping("/sendMessage")
    public ResponseEntity enqueue() {
        clientService.sendMessage("Processar recorrencia 1");
        return ResponseEntity.ok().build();
    }
}
