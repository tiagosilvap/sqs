package com.example.sqs.service;

public interface BaseApiService<D> {
    void process(D data);
}
