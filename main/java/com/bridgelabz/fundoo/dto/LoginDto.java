package com.bridgelabz.fundoo.dto;

import javax.validation.constraints.Email;
/**
 * purpose:Dto Class for Login
 */
import javax.validation.constraints.Size;


import lombok.Data;
@Data
public class LoginDto {
	@Email
	private String email;
	@Size(min=8,max=15,message ="invalid password" )
	private String password;
	public LoginDto(@Email String email, @Size(min = 8, max = 15, message = "invalid password") String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public LoginDto() {
	}
	



}
