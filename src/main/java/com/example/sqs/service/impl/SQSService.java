package com.example.sqs.service.impl;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.example.sqs.service.MessagingService;
import org.springframework.stereotype.Service;

@Service
public class SQSService implements MessagingService<String> {
    
    private final AmazonSQSAsync sqs;
    
    public SQSService(AmazonSQSAsync amazonSQS) {
        this.sqs = amazonSQS;
    }
    
    @Override
    public void sendMessage(String message) {
        String queueURL = sqs.getQueueUrl("test").getQueueUrl();
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueURL)
                .withMessageBody(message)
                .withDelaySeconds(1);
        sqs.sendMessage(request);
    }
}
