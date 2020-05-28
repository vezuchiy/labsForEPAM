package com.app.models;

import java.util.concurrent.locks.ReentrantLock;

public class CallCounter {
    private int callsCount;
    private ReentrantLock lock;

    public CallCounter(int count, ReentrantLock lock) {
        this.callsCount = count;
        this.lock = lock;
    }

    public int getCallsCount() {
        return this.callsCount;
    }

    public void nullifyCallsCount() {
        this.callsCount = 0;
    }

    public void incrementCounter() {
        this.lock.lock();
        try {
            this.callsCount++;
        } finally {
            lock.unlock();
        }
    }
}
