package com.njkol;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class TestVarHandle {

	@Test
	public void testBookPublic() throws Throwable {

		VarHandle PUBLIC_TEST_VARIABLE = MethodHandles
				  .lookup()
				  .in(VariableHandlesUnitTest.class)
				  .findVarHandle(VariableHandlesUnitTest.class, "publicTestVariable", int.class);
		
		assertEquals(1, PUBLIC_TEST_VARIABLE.coordinateTypes().size());
		assertEquals(VariableHandlesUnitTest.class, PUBLIC_TEST_VARIABLE.coordinateTypes().get(0));
	}
	
}
