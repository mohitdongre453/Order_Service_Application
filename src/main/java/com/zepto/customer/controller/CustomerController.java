package com.zepto.customer.controller;

import com.zepto.customer.repository.CustomerRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zepto.customer.request.CustomerRequest;
import com.zepto.customer.response.CustomerResponse;
import com.zepto.customer.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	@PostMapping("/createCustomer")
	public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest){
		CustomerResponse customerResponse=customerService.createCustomer(customerRequest);
		return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
	}
	@GetMapping("/allCustomer")
	public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
		List<CustomerResponse> customerList=customerService.findAllCustomer();
		return ResponseEntity.status(HttpStatus.OK).body(customerList);
	}
	@GetMapping("/customerById")
	public ResponseEntity<CustomerResponse> getCustomerById(@RequestParam(value = "id") Long customerId){
		CustomerResponse customerResponse=customerService.findCustomerById(customerId);
		return ResponseEntity.status(HttpStatus.FOUND).body(customerResponse);
	}
}
