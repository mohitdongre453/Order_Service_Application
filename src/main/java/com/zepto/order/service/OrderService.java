package com.zepto.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zepto.customer.entity.CustomerEntity;
import com.zepto.customer.repository.CustomerRepository;
import com.zepto.order.entity.OrderEntity;
import com.zepto.order.exception.OrderDoesNotExistsException;
import com.zepto.order.repository.OrderRepository;
import com.zepto.order.request.OrderRequest;
import com.zepto.order.response.OrderResponse;

@Service
public class OrderService {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	OrderRepository orderRepository;
	public OrderResponse createOrder(OrderRequest orderRequest) {
		Long customerId=orderRequest.getCustomerId();
		CustomerEntity parentEntity=customerRepository.findById(customerId).get();
		OrderEntity childEntity=new OrderEntity();
		childEntity.setCustomer(parentEntity);
		childEntity.setOrderNumber(generateOrderNumber());
		childEntity.setOrderDate(LocalDateTime.now());
		childEntity.setStatus("PLACED");
		childEntity.setTotalAmount(orderRequest.getTotalAmount()*orderRequest.getQuantity());
		childEntity.setProductName(orderRequest.getProductName());
		childEntity.setQuantity(orderRequest.getQuantity());
		OrderEntity responseEntity=orderRepository.save(childEntity);
		OrderResponse orderResponse=new OrderResponse();
		orderResponse.setCustomerId(customerId);
		orderResponse.setOrderId(responseEntity.getId());
		orderResponse.setProductName(responseEntity.getProductName());
		orderResponse.setQuantity(responseEntity.getQuantity());
		orderResponse.setTotalAmount(orderRequest.getTotalAmount()*orderRequest.getQuantity());
		return orderResponse;
		
	}
	public List<OrderResponse> findAllOrderByCustomer(Long customerId){
		List<OrderEntity> orderList=null;
		try {
		      orderList=orderRepository.findByCustomerCustomerId(customerId);
		}
		catch (OrderDoesNotExistsException e) {
			throw new OrderDoesNotExistsException("Order not available for customerId :"+ customerId);
		}
		List<OrderResponse> orderResponseList=new ArrayList<OrderResponse>();
		for(OrderEntity orderEntity: orderList) {
			OrderResponse orderResponse=new OrderResponse();
			orderResponse.setCustomerId(customerId);
			orderResponse.setOrderId(orderEntity.getId());
			orderResponse.setProductName(orderEntity.getProductName());
			orderResponse.setQuantity(orderEntity.getQuantity());
			orderResponse.setTotalAmount(orderEntity.getTotalAmount()*orderEntity.getQuantity());
			orderResponseList.add(orderResponse);
		}
		return orderResponseList;
	}
	public String generateOrderNumber() {
		int number = 10000 + new java.util.Random().nextInt(90000);
	    return String.valueOf(number);
	}
}
