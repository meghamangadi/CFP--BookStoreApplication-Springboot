package com.bridgelabz.bookstoreapp.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class LoginDto {
	
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password ;

}
