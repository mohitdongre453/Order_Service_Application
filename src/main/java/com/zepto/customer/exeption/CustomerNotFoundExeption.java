package com.zepto.customer.exeption;

public class CustomerNotFoundExeption extends RuntimeException{
	public CustomerNotFoundExeption(String message) {
		super(message);
	}
}
