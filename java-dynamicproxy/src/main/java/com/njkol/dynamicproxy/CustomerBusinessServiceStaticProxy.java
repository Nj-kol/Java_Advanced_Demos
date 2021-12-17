package com.njkol.dynamicproxy;

public class CustomerBusinessServiceStaticProxy implements CustomerService {

	CustomerService service = null;

	public CustomerBusinessServiceStaticProxy(CustomerService service) {
		this.service = service;
	}

	public void saveCustomer() {
          System.out.println("Apply Some advice ..");
          service.saveCustomer();
	}
}
