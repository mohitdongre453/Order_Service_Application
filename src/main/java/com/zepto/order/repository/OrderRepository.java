package com.zepto.order.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zepto.order.entity.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity,Long>{
	List<OrderEntity> findByCustomerCustomerId(Long customerId);

}
