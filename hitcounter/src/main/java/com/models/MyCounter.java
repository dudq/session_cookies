package com.models;

public class MyCounter {
    private int count;

    public MyCounter() {
    }

    public MyCounter(int count) {
        this.count = count;
    }

    public int increment() {
        return this.count++;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
