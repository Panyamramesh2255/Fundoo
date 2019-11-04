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
	private String profilepic;
	public RegistrationModel(String id, String userName, String passWord, boolean verify, String email,
			String mobileNumber, String profilepic) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.verify = verify;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.profilepic = profilepic;
	}
	public RegistrationModel()
	{
		
	}
	

}
