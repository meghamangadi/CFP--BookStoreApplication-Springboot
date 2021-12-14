package com.bridgelabz.bookstoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstoreapp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
     
	@Query(value = "SELECT * FROM orders where cancel = false", nativeQuery = true)
	 public List<Order> getAllOrders();
	
	@Query(value = "SELECT * FROM orders where userId = :user_id", nativeQuery = true)
	 public List<Order> getAllOrdersByUserId(Long user_id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "Update orders set cancel = true where userId =:user_id", nativeQuery = true)
	 public Order calcelOrder(Long user_id);
	
	
	
}
