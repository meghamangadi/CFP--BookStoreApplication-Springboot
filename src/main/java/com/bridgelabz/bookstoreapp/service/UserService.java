package com.bridgelabz.bookstoreapp.service;



import com.bridgelabz.bookstoreapp.dto.LoginDto;
import com.bridgelabz.bookstoreapp.dto.RegisterDto;
import com.bridgelabz.bookstoreapp.dto.ResetPassword;
import com.bridgelabz.bookstoreapp.entity.Users;
import com.bridgelabz.bookstoreapp.exception.UserException;
import com.bridgelabz.bookstoreapp.response.UserResponse;

public interface UserService {
	
	public Users register( RegisterDto registerDto) throws UserException;

	public Users delete(String userId) throws UserException  ;
	
	public Users update(String userId, RegisterDto registerDto) throws UserException  ;
	public UserResponse login (LoginDto loginDto) throws UserException; ;
	boolean verifyUser(String token) throws UserException;
	boolean resetPassword(ResetPassword resetPassword, String token) throws UserException;
	Users forgetPassword(String email) throws UserException;

}
 