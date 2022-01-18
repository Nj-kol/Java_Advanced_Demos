package com.njkol;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class TestMethodHandles {

	@Test
	public void testBookPublic() throws Throwable {

		Book book = new Book("ISBN-1234", "Effective Java");

		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle getTitleMH = lookup.findGetter(Book.class, "title", String.class);
		String expected = (String) getTitleMH.invoke(book);
		System.out.println(expected);
		assertEquals("Effective Java", expected);
	}

	@Test
	public void givenPrivateMethod_whenCreatingItsMethodHandle_thenCorrectlyInvoked() throws Throwable {
		
		Book book = new Book("ISBN-123", "Java in Action");
		
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		Method formatBookMethod = Book.class.getDeclaredMethod("formatBook");
		formatBookMethod.setAccessible(true);
		MethodHandle formatBookMH = lookup.unreflect(formatBookMethod);
		String expected = (String)formatBookMH.invoke(book);
		System.out.println(expected);
		assertEquals("ISBN-123 > Java in Action", expected);
	}

	 @Test
	public void testString() throws Throwable {

		MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
		MethodType mt = MethodType.methodType(String.class, char.class, char.class);
		MethodHandle replaceMH = publicLookup.findVirtual(String.class, "replace", mt);
		String output = (String) replaceMH.invoke("jovo", Character.valueOf('o'), 'a');
		System.out.println(output);
	}
	
}
