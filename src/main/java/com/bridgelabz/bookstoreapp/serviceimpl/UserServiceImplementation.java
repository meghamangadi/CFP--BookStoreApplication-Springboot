package com.bridgelabz.bookstoreapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.LoginDto;
import com.bridgelabz.bookstoreapp.dto.RegisterDto;
import com.bridgelabz.bookstoreapp.dto.ResetPassword;
import com.bridgelabz.bookstoreapp.entity.Users;
import com.bridgelabz.bookstoreapp.exception.UserException;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.response.UserResponse;
import com.bridgelabz.bookstoreapp.service.UserService;
import com.bridgelabz.bookstoreapp.utils.MailService;
import com.bridgelabz.bookstoreapp.utils.TokenUtils;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	@Autowired
	private TokenUtils tokenUtils;

	@Override
	public Users register(RegisterDto userInfoDto) throws UserException {

		if (userRepository.FindByEmail(userInfoDto.getEmail()).isPresent()) {
			throw new UserException(HttpStatus.NOT_ACCEPTABLE, "EMAIL_ID_ALREADY_PRASENT");
		}
		Users users = new Users();
		users.setEmail(userInfoDto.getEmail());
		users.setMobileNumber(userInfoDto.getMobileNumber());
		users.setName(userInfoDto.getName());
		users.setPassword(userInfoDto.getPassword());
		users.setVerify(false);
		users.setDateOfBirth(userInfoDto.getDateOfBirth());
		users.setRegisterDate(userInfoDto.getRegisterDate());
		users.setUpdatedDate(userInfoDto.getUpdatedDate());
		users.setKyc(userInfoDto.getKyc());
		userRepository.save(users);
		String Id = tokenUtils.createToken(users.getUserId());
		String mailResponse = "http://localhost:8085/user/verifyemail/" + Id;
		MailService.sendEmail(users.getEmail(), "Verification link", mailResponse);
		return users;
	}

	@Override
	public UserResponse login(LoginDto loginDto) throws UserException {
		Users user = userRepository.FindByEmail(loginDto.getEmail())
				.orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "USER_NOT_FOUND_EXCEPTION_MESSAGE"));
		if (!(loginDto.getPassword()).equals(user.getPassword())) {
			throw new UserException(HttpStatus.ACCEPTED, "please enter correct email and password ");
		}
		UserResponse useresponse = new UserResponse();
		useresponse.setDateOfBirth(user.getDateOfBirth());
		useresponse.setEmail(user.getEmail());
		useresponse.setKyc(user.getKyc());
		useresponse.setMobileNumber(user.getMobileNumber());
		useresponse.setName(user.getName());
		useresponse.setRegisterDate(user.getRegisterDate());
		useresponse.setVerify(false);
		useresponse.setUserId(user.getUserId());
		useresponse.setUpdatedDate(user.getUpdatedDate());
		return useresponse;
	}

	@Override
	public Users update(String userId, RegisterDto userInfoDto) throws UserException {
		Long Id = tokenUtils.decodeToken(userId);
		Users users = userRepository.findById(Id)
				.orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "USER_NOT_FOUND_EXCEPTION_MESSAGE"));
		users.setEmail(userInfoDto.getEmail());
		users.setMobileNumber(userInfoDto.getMobileNumber());
		users.setName(userInfoDto.getName());
		users.setPassword(userInfoDto.getPassword());		 
		users.setDateOfBirth(userInfoDto.getDateOfBirth());
		users.setRegisterDate(userInfoDto.getRegisterDate());
		users.setUpdatedDate(userInfoDto.getUpdatedDate());
		users.setKyc(userInfoDto.getKyc());
		userRepository.save(users);
		return users;

	}

	@Override
	public Users delete(String userId) throws UserException {
		Long Id = tokenUtils.decodeToken(userId);				 
		userRepository.deleteById(Id);
		return null;
	}

	@Override
	public boolean verifyUser(String token) throws UserException {
		Long Id = tokenUtils.decodeToken(token);
		Users userInfo = userRepository.findById(Id).orElseThrow(
				() -> new UserException(HttpStatus.NOT_FOUND,"User Does not exist..!"));
		if (userInfo.isVerify()!= true) {
			userInfo.setVerify(true);			 
			userRepository.save(userInfo);
			throw new UserException(HttpStatus.ACCEPTED,"User Verification sucessfull");
		}

		throw new UserException(HttpStatus.BAD_REQUEST,"User already Verified");
	}

	@Override
	public Users forgetPassword(String email) throws UserException {
		Users userMail = userRepository.FindByEmail(email).orElseThrow(
				() -> new UserException(HttpStatus.NOT_FOUND,"User Does not exist..!"));

		if (userMail.isVerify() == true) {
			String Id = tokenUtils.createToken(userMail.getUserId());
			String responsemail = "http://localhost:8085/resetpassword/"+Id;
			MailService.sendEmail(userMail.getEmail(), "Reset your password", responsemail);
			return userMail;
		}
		throw new UserException(HttpStatus.NOT_FOUND, "User Does not exist..!");
	}

	@Override
	public boolean resetPassword(ResetPassword resetPassword, String token) throws UserException {
		Long Id = tokenUtils.decodeToken(token);
		Users user = userRepository.findById(Id).orElseThrow(
				() -> new UserException(HttpStatus.NOT_FOUND,"User Does not exist.."));
		if (user.isVerify()) {
			user.setPassword(resetPassword.getConfirmPassword());
			userRepository.updateUserPassword(resetPassword.getConfirmPassword(), Id);
		}
		return true;
	}

}
