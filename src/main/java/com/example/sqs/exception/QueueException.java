package com.example.sqs.exception;

public class QueueException extends Exception {

    public QueueException(String message) {
        super(message);
    }

    public QueueException(String message, Throwable cause) {
        super(message, cause);
    }

}
