package com.bridgelabz.bookstoreapp.service;



import com.bridgelabz.bookstoreapp.dto.LoginDto;
import com.bridgelabz.bookstoreapp.dto.RegisterDto;
import com.bridgelabz.bookstoreapp.entity.Users;
import com.bridgelabz.bookstoreapp.exception.UserException;
import com.bridgelabz.bookstoreapp.response.UserResponse;

public interface UserService {
	
	public Users register( RegisterDto registerDto) throws UserException;

	public Users delete(Long userId) throws UserException  ;
	
	public Users update(Long userId, RegisterDto registerDto) throws UserException  ;
	public UserResponse login (LoginDto loginDto) throws UserException; ;
    
}
 