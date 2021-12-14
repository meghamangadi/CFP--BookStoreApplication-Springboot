package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

@Data
public class OrderDto {
	private int price;
	private int quantity;
	private String address;
	private Long bookId;
	private boolean cancel = false;
}
