package com.bridgelabz.bookstoreapp.response;

import java.sql.Date; 
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
	
    private Long userId;
	private String name;
	private String email;	 
	private Long mobileNumber;
	private String kyc;
	private Date dateOfBirth;
	private Date  updatedDate;
	private Date registerDate;
	private boolean verify;



}
