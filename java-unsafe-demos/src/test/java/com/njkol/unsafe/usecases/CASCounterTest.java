package com.njkol.unsafe.usecases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CASCounterTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetCounter() throws Exception {
		int NUM_OF_THREADS = 1000;
		int NUM_OF_INCREMENTS = 10000;
		
		ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
		CASCounter casCounter = new CASCounter();

		IntStream.rangeClosed(0, NUM_OF_THREADS - 1)
		  .forEach(i -> service.submit(() -> IntStream
		    .rangeClosed(0, NUM_OF_INCREMENTS - 1)
		    .forEach(j -> casCounter.increment())));
		
		// give time for all values to converge
		Thread.sleep(1000);
		
		assertEquals(NUM_OF_INCREMENTS * NUM_OF_THREADS, casCounter.getCounter());
	}

}
