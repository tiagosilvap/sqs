package com.example.sqs.service;

public interface MessagingService<T> {
    void sendMessage(T message);
}
