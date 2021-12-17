package com.njkol.unsafe.usecases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OffHeapArrayTest {

	private OffHeapArray array;
	private long SUPER_SIZE;

	@BeforeEach
	void setUp() throws Exception {
		SUPER_SIZE = (long) Integer.MAX_VALUE * 2;
		array = new OffHeapArray(SUPER_SIZE);
	}

	@Test
	void testGet() throws NoSuchFieldException, IllegalAccessException {

		int sum = 0;

		// Add 3 100 times
		for (int i = 0; i < 100; i++) {
			array.set((long) Integer.MAX_VALUE + i, (byte) 3);
			sum += array.get((long) Integer.MAX_VALUE + i);
		}

		assertEquals(array.size(), SUPER_SIZE);
		assertEquals(sum, 300);
	}
}
