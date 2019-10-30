package com.bridgelabz.fundoo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;


import lombok.Data;
@Data
public class RegistrationDto {
	@NotNull
	private String userName;
	@Length(min = 8,max = 15,message = "lenght should be minimum of 8 and maximum of 15 charecters")
	private String passWord;
	@Email(message = "invalid email !")
	@Indexed(unique = true)
	private String email;
	@Size(min = 10,max = 10,message = "invalid mobile number!")
	private String mobileNumber;

}
