package com.bridgelabz.bookstoreapp.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Data
@Table(name = "Users")

public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column
	@NotNull
	private String name;

	@Column
	@NotNull
	private String password;

	@NotNull
	@Column
	private String email;
	@Column
	@NotNull
	private Long mobileNumber;	
	@Column
	@NotNull
	private String kyc;
	@Column
	@NotNull
	private Date dateOfBirth;
	@Column
	@NotNull
	private Date  updatedDate;
	@Column
	@NotNull
	private Date registerDate;
	@Column
	@NotNull
	private boolean  Verify;


}
