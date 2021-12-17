package com.njkol.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 
 * @author Nilanjan Sarkar
 *
 */
public class DynamicProxyDemo {

	public static void main(String[] args) {

		ClassLoader cl = DynamicProxyDemo.class.getClassLoader();

		// Trying proxy with customer service
		CustomerService cs = new CustomerBusinessService();
		
		InvocationHandler handler1 = getAuditLogAdvice(cs);
		
		CustomerService csProxiedService = (CustomerService) Proxy.newProxyInstance(cl,new Class[] { CustomerService.class }, handler1);
		csProxiedService.saveCustomer();

		// Trying proxy with product service
		ProductService ps = new ProductBusinessService();
		
		InvocationHandler handler2 = getAuditLogAdvice(cs);
		
		ProductService psProxiedService = (ProductService) Proxy.newProxyInstance(cl,new Class[] { ProductService.class }, handler2);

		psProxiedService.saveProduct();
	}

	private static AuditLogAdvice getAuditLogAdvice(Object obj) {
		return new AuditLogAdvice(obj);
	}
}
