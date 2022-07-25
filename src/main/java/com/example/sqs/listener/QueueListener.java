package com.example.sqs.listener;

import com.example.sqs.exception.QueueException;
import com.example.sqs.service.BaseApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;

import java.time.Duration;
import java.time.Instant;

public abstract class QueueListener {

    private static final String RECEIVED_QUEUE = "Received queue {} [{}]";
    private static final String QUEUE_PROCESSED_SUCCESSFULLY = "Queue {} processed successfully.";
    private static final String PROCESSING_COMPLETED = "Finished process of {} [{}]. Process completed in {} milliseconds.";
    private final Logger log = LoggerFactory.getLogger(getClass());

    <D> void processMessage(D vo, Acknowledgment acknowledgment, String queueName, BaseApiService<D> service)
            throws QueueException {

        Instant start = Instant.now();
        log.info(RECEIVED_QUEUE, queueName, vo);
        try {
            service.process(vo);
            processSuccess(queueName, acknowledgment);
        } catch (Exception e) {
            processError(queueName, vo, e);
        } finally {
            log.info(PROCESSING_COMPLETED, queueName, vo, Duration.between(start, Instant.now()).toMillis());
        }

    }

    void processSuccess(String queueName, Acknowledgment acknowledgment) {
        acknowledgment.acknowledge();
        log.info(QUEUE_PROCESSED_SUCCESSFULLY, queueName);
    }

    private <D> void processError(String queueName, D vo, Exception e) throws QueueException {
        String msg = "Error to processMessage [" + queueName + "] data[" + vo + "]";
        log.error(msg, e);
        throw new QueueException(msg, e);
        /* if (queueName.toLowerCase().contains("dead")) {
            throw new QueueException(msg, e);
        } */
    }
}
