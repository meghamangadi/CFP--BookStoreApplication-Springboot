package com.bridgelabz.bookstoreapp.service;

import java.util.List;

import com.bridgelabz.bookstoreapp.dto.OrderDto;
import com.bridgelabz.bookstoreapp.entity.Order;

public interface OrderService {
     Order getBookById(Long order_id);
	
	Order placeOrder(String token, OrderDto  orderDTO);
	
	Order updateOrder(Long order_id, OrderDto orderDTO);
	
	List<Order> getAllOrders();
	
	List<Order> getAllOrderForUser(String token);
	
	String cancelOrder(Long order_id);
}
