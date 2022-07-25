package com.example.sqs.service.impl;

import com.example.sqs.service.BaseApiService;
import com.example.sqs.service.MessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements BaseApiService<String> {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    private final MessagingService messagingService;
    
    public ClientService(MessagingService messagingService) {
        this.messagingService = messagingService;
    }
    
    public void sendMessage(String message) {
        log.info("Send message: {}", message);
        messagingService.sendMessage(message);
    }
    
    @Override
    public void process(String data) {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            log.error("Error", e);
        }
    }
}
