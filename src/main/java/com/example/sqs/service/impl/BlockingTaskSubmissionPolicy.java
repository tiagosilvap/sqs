package com.example.sqs.service.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BlockingTaskSubmissionPolicy implements RejectedExecutionHandler {
    private final long timeout;
    
    public BlockingTaskSubmissionPolicy(long timeout) {
        this.timeout = timeout;
    }
    
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            BlockingQueue queue = executor.getQueue();
            //As we are expecting the value of thread pool queue is 0
            if (!queue.offer(r, this.timeout, TimeUnit.MILLISECONDS)) {
                throw new RejectedExecutionException("The Thread Pool is full");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
