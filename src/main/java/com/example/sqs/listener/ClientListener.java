//package com.example.sqs.listener;
//
//import com.example.sqs.exception.QueueException;
//import com.example.sqs.service.impl.ClientService;
//import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
//import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
//import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ClientListener extends QueueListener {
//
//    private final ClientService clientService;
//
//    public ClientListener(ClientService clientService) {
//        this.clientService = clientService;
//    }
//
//    @SqsListener(value = "test", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
//    public void receiveSQSMessage(String message,
//                                  Acknowledgment acknowledgment,
//                                  @Header(name = "LogicalResourceId") String queueName) throws QueueException {
//        processMessage(message, acknowledgment, queueName, clientService);
//    }
//}
