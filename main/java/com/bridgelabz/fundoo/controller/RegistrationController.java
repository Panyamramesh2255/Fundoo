package com.bridgelabz.fundoo.controller;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDto;
import com.bridgelabz.fundoo.model.EmailDataModel;
import com.bridgelabz.fundoo.model.RegistrationModel;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.service.IRegistrationService;
import com.bridgelabz.fundoo.service.RegistrationService;
import com.bridgelabz.fundoo.util.Util;
import com.sun.mail.handlers.multipart_mixed;

import java.io.IOException;
import java.lang.String;
/**
 * purpose:User Registration,Details
 * @author PanyamRamesh
 *
 */
@RequestMapping("/user")
@RestController
public class RegistrationController {
	@Autowired
	IRegistrationService registrationservice;
	@Autowired
	Util util;
	@Autowired
	Environment environment;

	/**
	 * purpose: Registration
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
	public ResponseEntity<Response> verify(@RequestParam String token) {
		String email=util.decode(token);
		Response response=registrationservice.verifying(email);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpsoe:Login
	 * 
	 * @param loginDto
	 * @return
	 * @throws LoginException
	 */
	@GetMapping("/login")
	public ResponseEntity<Response> login(@Valid @RequestHeader String email, @RequestHeader String  password) throws LoginException {
		Response response = registrationservice.verify(email,password);
		
			return new ResponseEntity<Response>(response, HttpStatus.OK);

		
		
	}

	/**
	 * purpose:forgotpassword
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping("/forgotpassword")
	public ResponseEntity<Response> send(@RequestHeader String email) {
		String token=util.encode(email);
		Response response=registrationservice.forgotPassword(email,token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: Reset password
	 * 
	 * @param token
	 * @param password
	 * @return
	 */
	@PutMapping("/password")
	public ResponseEntity<Response> decode(@RequestHeader String token, @RequestHeader String password) {
		String decodedstring = util.decode(token);
		Response response = registrationservice.resetPassword(decodedstring, password);
		
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		

	}

	/**
	 * purpose: Delete User
	 * 
	 * @param username
	 * @return
	 */
	@DeleteMapping("/user")
	public ResponseEntity<Response> delete(@RequestParam String username) {
		Response response=registrationservice.delete(username);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: Update User Details
	 * 
	 * @param email
	 * @param username
	 * @return
	 */
	@PutMapping("/user")
	public ResponseEntity<Response> updateEmail(@Valid @RequestParam String email, @Valid @RequestParam String username) {
		Response response = registrationservice.getData(email, username);
		

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	/**
	 * purpose: Sending Email
	 * 
	 * @return
	 * @throws MessagingException
	 */
	@GetMapping("/sendemail")
	public ResponseEntity<Response> sendemail() throws MessagingException {
		Response response=registrationservice.sendEmail("panyamramesh2255@gmail.com", "Test mail", "Test");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	@PostMapping("/profilepic")
	public ResponseEntity<Response>addprofilepic(@RequestParam MultipartFile file,@RequestParam String id,@RequestHeader String token) throws IOException
	{
		Response response=registrationservice.addProfilePic(file,id,util.decode(token));
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@DeleteMapping("/profilepic")
	public ResponseEntity<Response>deleteprofilepic(@RequestParam String id,@RequestHeader String token)
	{  
		String email=util.decode(token);
		Response response=registrationservice.deleteProfilePic(id,email);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PutMapping("/profilepic")
	public ResponseEntity<Response>updateProfilepic(@RequestParam MultipartFile file,@RequestParam String id,@RequestHeader String token) throws IOException
	{
		Response response=registrationservice.updateProfilePic(file,id,util.decode(token));
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
