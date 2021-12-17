package com.njkol.unsafe.usecases;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("restriction")
class SecretHolderTest {

	private Unsafe unsafe;

	@BeforeEach
	void setUp() throws Exception {

		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		unsafe = (Unsafe) f.get(null);
	}

	@Test
	void test() throws NoSuchFieldException, SecurityException {
		SecretHolder secretHolder = new SecretHolder();

		Field f = secretHolder.getClass().getDeclaredField("SECRET_VALUE");
		unsafe.putInt(secretHolder, unsafe.objectFieldOffset(f), 1);
		assertTrue(secretHolder.secretIsDisclosed());
	}
}
