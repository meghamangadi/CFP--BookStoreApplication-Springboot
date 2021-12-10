package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

@Data
public class BookDto {

	private String bookName;
	private String bookAuthor;	
	private Long quantity;
	private double bookPrice;
	private String logo;	 
	private String bookDescription;	 
  
}
