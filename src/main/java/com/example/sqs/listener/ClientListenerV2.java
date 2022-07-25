package com.example.sqs.listener;

import com.example.sqs.exception.QueueException;
import com.example.sqs.service.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class ClientListenerV2 extends QueueListener {
    
    @Autowired
    @Qualifier("threadPoolQueue")
    private AsyncTaskExecutor threadPoolQueue;

    private final ClientService clientService;
    
    public ClientListenerV2(ClientService clientService) {
        this.clientService = clientService;
    }
    
    @SqsListener(value = "test", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void receiveSQSMessage(String message,
                                  Acknowledgment acknowledgment,
                                  @Header(name = "LogicalResourceId") String queueName) {
        threadPoolQueue.submit(() -> {
            try {
                processMessage(message, acknowledgment, queueName, clientService);
            } catch (Exception e) {
                throw new RuntimeException("Failed to submit task to the worker");
            }
        });
    }
}
