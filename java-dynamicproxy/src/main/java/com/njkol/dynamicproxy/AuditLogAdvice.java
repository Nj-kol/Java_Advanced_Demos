package com.njkol.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * A Simple method audit service that can wrap around any method of any class
 * 
 * @author Nilanjan Sarkar
 *
 */
public class AuditLogAdvice implements InvocationHandler {

	private Object target;

	public AuditLogAdvice(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Entering Method : " + method.getName());
		Object result = method.invoke(target, args);
		System.out.println("Leaving Method : " + method.getName());
		return result;
	}
}
