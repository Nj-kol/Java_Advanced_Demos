package com.njkol.unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import com.njkol.unsafe.usecases.CASCounter;
import com.njkol.unsafe.usecases.OffHeapArray;

public class UnsafeTester {

	public static void main(String[] args) throws Exception {
		testOffHeap();
	}

	
	private static void testOffHeap() throws NoSuchFieldException, IllegalAccessException {
		
		long SUPER_SIZE = (long) Integer.MAX_VALUE * 2;
		
		OffHeapArray array = new OffHeapArray(SUPER_SIZE);
		
		int sum = 0;

		// Add 3 100 times
		for (int i = 0; i < 100; i++) {
		    array.set((long) Integer.MAX_VALUE + i, (byte) 3);
		    sum += array.get((long) Integer.MAX_VALUE + i);
		}
		
		System.out.println("The sum is : "+sum);
		
		array.freeMemory();
	}
}
