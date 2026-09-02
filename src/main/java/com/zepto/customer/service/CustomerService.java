package com.zepto.customer.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zepto.customer.entity.CustomerEntity;
import com.zepto.customer.exeption.CustomerNotFoundExeption;
import com.zepto.customer.repository.CustomerRepository;
import com.zepto.customer.request.CustomerRequest;
import com.zepto.customer.response.CustomerResponse;
import com.zepto.order.entity.OrderEntity;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	public CustomerResponse createCustomer(CustomerRequest customerRequest) {
		CustomerEntity customerEntity=new CustomerEntity();
		customerEntity.setCustomerName(customerRequest.getCustomerName());
		customerEntity.setEmail(customerRequest.getCustomerEmail());
		customerEntity.setCustomerMobileNumber(customerRequest.getCustomerMobileNumber());
		customerEntity.setAddress(customerRequest.getAddress());
		customerEntity.setCreatedAt(LocalDateTime.now());
		customerEntity.setUpdatedAt(LocalDateTime.now());
		customerEntity.setCustomerType(customerRequest.getCustomerType());
		CustomerEntity responseEntity=customerRepository.save(customerEntity);
		CustomerResponse customerResponse=new CustomerResponse();
		customerResponse.setCustomerName(responseEntity.getCustomerName());
		customerResponse.setAddress(responseEntity.getAddress());
		customerResponse.setCustomerMobileNumber(responseEntity.getCustomerMobileNumber());
		customerResponse.setCustomerType(responseEntity.getCustomerType());
		customerResponse.setId(responseEntity.getCustomerId());
		return customerResponse;
	}
	public List<CustomerResponse> findAllCustomer(){
		List<CustomerEntity> customerList=(List<CustomerEntity>) customerRepository.findAllCustomerWithOrder();
		List<CustomerResponse> customerResponseList=new ArrayList<CustomerResponse>();
		for(CustomerEntity responseEntity: customerList) {
			CustomerResponse customerResponse=new CustomerResponse();
			customerResponse.setCustomerName(responseEntity.getCustomerName());
			customerResponse.setAddress(responseEntity.getAddress());
			customerResponse.setCustomerMobileNumber(responseEntity.getCustomerMobileNumber());
			customerResponse.setCustomerType(responseEntity.getCustomerType());
			customerResponse.setId(responseEntity.getCustomerId());
			List<OrderEntity> orderList=responseEntity.getOrders();
			System.out.println(orderList.size());
			customerResponseList.add(customerResponse);
		}
		return customerResponseList;
	}
	public CustomerResponse findCustomerById(Long customerId) {
		CustomerEntity responseEntity = customerRepository.findById(customerId)
	            .orElseThrow(() ->
	                new CustomerNotFoundExeption(
	                    "Customer not found for customerId: " + customerId
	                )
	            );
		CustomerResponse customerResponse=new CustomerResponse();
		customerResponse.setCustomerName(responseEntity.getCustomerName());
		customerResponse.setAddress(responseEntity.getAddress());
		customerResponse.setCustomerMobileNumber(responseEntity.getCustomerMobileNumber());
		customerResponse.setCustomerType(responseEntity.getCustomerType());
		customerResponse.setId(responseEntity.getCustomerId());
		return customerResponse;
	}
}
