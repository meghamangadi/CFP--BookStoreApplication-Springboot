package com.bridgelabz.bookstoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.LoginDto;
import com.bridgelabz.bookstoreapp.dto.RegisterDto;
import com.bridgelabz.bookstoreapp.entity.Users;
import com.bridgelabz.bookstoreapp.exception.UserException;
import com.bridgelabz.bookstoreapp.response.Response;
import com.bridgelabz.bookstoreapp.response.UserResponse;
import com.bridgelabz.bookstoreapp.service.UserService;
import com.bridgelabz.bookstoreapp.utils.TokenUtils;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service ;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	
	@PostMapping("/register")
	public ResponseEntity<Response>registration(@RequestBody RegisterDto registerDto ) throws UserException {
		Users user= service.register(registerDto);
		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "USER_REGISTER_SUCESSFULL", user));
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Response>update(@RequestParam Long userId, @RequestBody RegisterDto registerDto ) throws UserException {
		Users user= service.update(userId,registerDto);
		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "USER_REGISTER_SUCESSFULL", user));
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response>delete(@RequestParam Long userId) throws UserException {
		Users user= service.delete(userId);
		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "USER_DELETED_SUCESSFULL", user));
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginDto loginDto) throws UserException {	
		 
		UserResponse user = service.login(loginDto);
		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "Login Successful", tokenUtils.createToken(user.getUserId())));
		
	}

}
