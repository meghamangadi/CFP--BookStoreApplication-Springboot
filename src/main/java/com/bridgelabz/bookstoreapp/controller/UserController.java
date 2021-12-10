package com.bridgelabz.bookstoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.LoginDto;
import com.bridgelabz.bookstoreapp.dto.RegisterDto;
import com.bridgelabz.bookstoreapp.dto.ResetPassword;
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
				.body(new Response(HttpStatus.ACCEPTED, "USER_REGISTER_SUCESSFULL", tokenUtils.createToken(user.getUserId())));
		
	}
	 
	@GetMapping("/verifyemail/{token}")
	public ResponseEntity<Response> verification(@PathVariable("token") String token) throws UserException {

		boolean user = service.verifyUser(token);
		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "User already Verified", user));
	}
	 
	@PostMapping("/forgetpassword")
	public ResponseEntity<Response> forgetPassword(@RequestParam String email) throws UserException {
		 service.forgetPassword(email);
		return ResponseEntity.ok().body(new Response(HttpStatus.ACCEPTED, "reset password mail send to email", email));

	}
	@PutMapping("/resetpassword/{token}")
	public ResponseEntity<Response> resetPassword(@RequestBody ResetPassword password,
			  @PathVariable("token") String token) throws UserException {
		 		boolean result = service.resetPassword(password, token);
		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "Password Reste Successfully..!", result));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Response>update(@PathVariable("token") String token, @RequestBody RegisterDto registerDto ) throws UserException {
		Users user= service.update(token,registerDto);
		return ResponseEntity.ok()
				.body(new Response(HttpStatus.ACCEPTED, "USER_REGISTER_SUCESSFULL", user));
		
	}
	
	@DeleteMapping("/delete/{token}")
	public ResponseEntity<Response>delete(@PathVariable("token") String token) throws UserException {
		Users user= service.delete(token);
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
