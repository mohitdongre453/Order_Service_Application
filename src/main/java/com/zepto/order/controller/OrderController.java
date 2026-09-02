package com.zepto.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zepto.order.repository.OrderRepository;
import com.zepto.order.request.OrderRequest;
import com.zepto.order.response.OrderResponse;
import com.zepto.order.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {
	@Autowired
	OrderService orderService;
	@PostMapping("/createOrder")
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
		OrderResponse orderResponse=orderService.createOrder(orderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
	}
	@GetMapping("/allOrderByCustomer")
	public ResponseEntity<List<OrderResponse>> getAllOrderByCustomer(@RequestParam(name = "id") Long customerId){
		List<OrderResponse> orderResponseList=orderService.findAllOrderByCustomer(customerId);
		return ResponseEntity.status(HttpStatus.FOUND).body(orderResponseList);
	}
}
