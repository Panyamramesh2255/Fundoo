package com.bridgelabz.fundoo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
@Data
public class LoginDto {
	@Email
	private String email;
	@Size(min=8,max=15,message ="invalid password" )
	private String password;



}
