package com.bridgelabz.bookstoreapp.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long order_id;

	private LocalDate orderDate;
	private int price;
	private int quantity;
	private String address;
	private Long userId;
	private Long bookId;
	private boolean cancel = false;
}
