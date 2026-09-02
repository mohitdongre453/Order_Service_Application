package com.zepto.order.exception;

public class OrderDoesNotExistsException extends RuntimeException{
	public OrderDoesNotExistsException(String message) {
		super(message);
	}
}
