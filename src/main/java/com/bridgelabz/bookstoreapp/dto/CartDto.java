package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

@Data
public class CartDto {
	//public Long userId;
	public Long bookId;
	public int quantity;
}
