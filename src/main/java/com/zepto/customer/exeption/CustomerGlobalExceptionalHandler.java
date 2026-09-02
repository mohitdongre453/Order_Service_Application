package com.zepto.customer.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zepto.customer.response.CustomerErrorResponse;

@RestControllerAdvice
public class CustomerGlobalExceptionalHandler {
	@ExceptionHandler(CustomerNotFoundExeption.class)
	public ResponseEntity<CustomerErrorResponse> handleCustomerNotFoundExeption(CustomerNotFoundExeption e){
		CustomerErrorResponse customerErrorResponse=new CustomerErrorResponse();
		customerErrorResponse.setErrorCode("ER-003");
		customerErrorResponse.setErrorMessage(e.getMessage());
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerErrorResponse);
	}
}
