package com.bridgelabz.fundoo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "DETAILS")
@Data
public class RegistrationModel {
	@Id
	private String id;
	private String userName;
	private String passWord;
	private boolean verify;
	private String email;
	private String mobileNumber;

}
