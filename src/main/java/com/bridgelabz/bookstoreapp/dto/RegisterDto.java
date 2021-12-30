package com.bridgelabz.bookstoreapp.dto;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterDto {

	private String name;
	private String email;
	private String password;
	private Long mobileNumber;
//	private String kyc;
//	private Date dateOfBirth;
//	private Date  updatedDate;
//	private Date registerDate;
	 

}
