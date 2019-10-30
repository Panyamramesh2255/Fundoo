package com.bridgelabz.fundoo.controller;

import javax.mail.MessagingException;
import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDto;
import com.bridgelabz.fundoo.model.RegistrationModel;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.service.RegistrationService;
import com.bridgelabz.fundoo.util.Util;

import java.lang.String;

@RequestMapping("/user")
@RestController

public class RegistrationController {
	@Autowired
	RegistrationService registrationservice;
	@Autowired
	Util util;

	/**
	 * purpose: Registartion
	 * 
	 * @param registrationDto
	 * @return
	 */
	@PostMapping("/registration")
	public ResponseEntity<Response> add(@Valid @RequestBody RegistrationDto registrationDto) {

		return new ResponseEntity<Response>(registrationservice.add(registrationDto), HttpStatus.OK);
	}

	/**
	 * prupose:verifying
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping("/verify")
	public ResponseEntity<String> verify(@RequestParam String token) {
		return new ResponseEntity<String>(registrationservice.verifying(token), HttpStatus.OK);
	}

	/**
	 * purpsoe:Login
	 * 
	 * @param loginDto
	 * @return
	 * @throws LoginException
	 */
	@GetMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto) throws LoginException {
		boolean status = registrationservice.verify(loginDto);
		if (status)
			return new ResponseEntity<String>("Log in sucess", HttpStatus.OK);

		return new ResponseEntity<String>("Log in Failure", HttpStatus.OK);
		// return new ResponseEntity<String>("sucsess", HttpStatus.OK);
	}

	/**
	 * purpose:forgotpassword
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping("/forgotpassword")
	public ResponseEntity<String> send(@RequestParam String email) {
		util.encode(email);
		return new ResponseEntity<String>("mail sended sucessfully !", HttpStatus.OK);
	}

	/**
	 * purpose: Resetpassword
	 * 
	 * @param token
	 * @param password
	 * @return
	 */
	@PutMapping("/resetpassword")
	public ResponseEntity<String> decode(@RequestHeader String token, @RequestHeader String password) {
		System.out.println("token recieved : " + token);
		String decodedstring = util.decode(token);
		System.out.println("decodedtoken recieved : " + decodedstring);
		boolean b = registrationservice.resetPassword(decodedstring, password);
		if (b)
			return new ResponseEntity<String>("password updated sucessfully", HttpStatus.OK);
		else
			return new ResponseEntity<String>("password not updated sucessfully", HttpStatus.OK);

	}

	/**
	 * purpose: Delete User
	 * 
	 * @param username
	 * @return
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam String username) {
		registrationservice.delete(username);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	/**
	 * purpose: Update User Details
	 * 
	 * @param email
	 * @param username
	 * @return
	 */
	@PutMapping("/update")
	public Response updateEmail(@Valid @RequestParam String email, @Valid @RequestParam String username) {
		RegistrationModel registrationModel = registrationservice.getData(email, username);
		

		return new Response(200, null, "updated sucessfully..");// <>(registrationModel, HttpStatus.OK);
	}

	/**
	 * purpose: Sending Email
	 * 
	 * @return
	 * @throws MessagingException
	 */
	@GetMapping("/sendemail")
	public ResponseEntity<String> sendemail() throws MessagingException {
		registrationservice.sendEmail("panyamramesh2255@gmail.com", "Test mail", "Test");
		return new ResponseEntity<String>("mail sended!!!!", HttpStatus.OK);
	}

}
