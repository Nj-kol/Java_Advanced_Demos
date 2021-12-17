package com.njkol.unsafe.usecases;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("restriction")
class InitializationOrderingTest {

	private Unsafe unsafe;

	@BeforeEach
	void setUp() throws Exception {

	    Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        unsafe= (Unsafe) f.get(null);
	}

	@Test
	void test() throws InstantiationException {
		
		InitializationOrdering o1 = new InitializationOrdering();
		assertEquals(o1.getA(), 1);

		InitializationOrdering o3 = (InitializationOrdering) unsafe.allocateInstance(InitializationOrdering.class);
		assertEquals(o3.getA(), 0);
	}
}
