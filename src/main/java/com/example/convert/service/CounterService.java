package com.example.convert.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public final class CounterService {
    private CounterService() { }
    private static AtomicInteger requestCount = new AtomicInteger(0);

    public static synchronized void incrementRequestCount() {
        requestCount.incrementAndGet();
    }

    public static synchronized int getRequestCount() {
        return requestCount.get();
    }
}
