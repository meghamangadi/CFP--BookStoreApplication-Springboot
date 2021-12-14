package com.bridgelabz.bookstoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.OrderDto;
import com.bridgelabz.bookstoreapp.response.Response;
import com.bridgelabz.bookstoreapp.service.OrderService;

@RestController
@RequestMapping("/order") 
public class OrderController {
	
	@Autowired
	private OrderService orderService ;
	
	@GetMapping(value = "/{orderId}")
	public ResponseEntity<Response> getOrderById(@PathVariable("orderId") Long order_id) {
		 
		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "order retriveds successfully ",orderService.getBookById(order_id)));
		
	}
	
	@GetMapping(value = "/getallorders")
	public ResponseEntity<Response> getAllOrders() {

		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "All order retrived successfully",orderService.getAllOrders()));
		
	}
	
	@GetMapping(value = "getorders/{token}")
	public ResponseEntity<Response> getOrderByUser(@PathVariable("token") String token) {

		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "your orders",orderService.getAllOrderForUser(token)));
	 
	}
	
	@PutMapping("cancel/{orderId}")
	public ResponseEntity<Response> cancelOrder(@PathVariable("orderId") Long orderId) {
		  
		orderService.cancelOrder(orderId);
		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "Order cancelled",orderService.cancelOrder(orderId)));
	  
	}
	
	@PostMapping("/add/{token}")
	public ResponseEntity<Response> placeOrder(@PathVariable("token") String token, @RequestBody OrderDto orderDTO) {
		 

		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "Order Placed Successfully",orderService.placeOrder(token, orderDTO)));
	 
		 
	}
	
}
