package com.bridgelabz.bookstoreapp.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.OrderDto;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.CartBooks;
import com.bridgelabz.bookstoreapp.entity.Order;
import com.bridgelabz.bookstoreapp.entity.Users;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.service.CartService;
import com.bridgelabz.bookstoreapp.service.OrderService;
import com.bridgelabz.bookstoreapp.service.UserService;
import com.bridgelabz.bookstoreapp.utils.TokenUtils;

@Service
public class OderServiceImplementation implements OrderService {
	
	
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	TokenUtils tokenUtil;
	
	@Autowired
	UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	CartService cartService;

	 
	
	@Override
	public Order getBookById(Long order_id) {
		return orderRepo.findById(order_id).orElse(null);
	}
	
	@Override
	public Order placeOrder(String token, OrderDto orderDTO) {
		Long id = tokenUtil.decodeToken(token);
	        Optional<Users> isPresent = userRepository.findById(id);
	        if(isPresent.isPresent()){
	            Order order = new Order();
	            order.setAddress(orderDTO.getAddress());
	            order.setBookId(orderDTO.getBookId());  
	           // order.setOrderDate(orderDTO);
	            order.setPrice(orderDTO.getPrice());
	            order.setQuantity(orderDTO.getQuantity());
	            order.setUserId(id);
	           return orderRepo.save(order);
	        }
	        return null;
	}

	@Override
	public Order updateOrder(Long order_id, OrderDto orderDTO) {
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepo.getAllOrders();
	}

	@Override
	public List<Order> getAllOrderForUser(String token) {
		Long id = tokenUtil.decodeToken(token);
		return orderRepo.getAllOrdersByUserId(id);
	}

	@Override
	public String cancelOrder(Long order_id) {
		Optional<Order> isPresent = orderRepo.findById(order_id);
		if(isPresent.isPresent()) {
			isPresent.get().setCancel(true);
			orderRepo.save(isPresent.get());
			return "Order is cancelled";
		} 
		
		return "order is not present";
	}}
