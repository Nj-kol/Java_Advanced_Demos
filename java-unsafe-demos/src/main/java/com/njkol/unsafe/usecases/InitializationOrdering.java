package com.njkol.unsafe.usecases;

public class InitializationOrdering {
	
    private long a;

    public InitializationOrdering() {
        this.a = 1;
    }

    public long getA() {
        return this.a;
    }
}