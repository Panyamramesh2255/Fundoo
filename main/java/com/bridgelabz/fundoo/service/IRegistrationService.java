package com.bridgelabz.fundoo.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.security.auth.login.LoginException;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDto;
import com.bridgelabz.fundoo.model.RegistrationModel;
import com.bridgelabz.fundoo.response.Response;
import com.sun.mail.handlers.multipart_mixed;

public interface IRegistrationService {

	public Response add(RegistrationDto registrationDto);

	public Response delete(String usernmae);

	public void update(String email, String username);

	public RegistrationModel getName(String email);

	public String getNumber(String username);

	public Response getData(String email, String username);

	public boolean verify(LoginDto loginDto) throws LoginException;

	public Response sendEmail(String to, String subject, String body) throws MessagingException;

	public boolean resetPassword(String decodedstring, String password);

	public Response verifying(String email);

	public Response addProfilePic(MultipartFile file, String id, String verifiedEmail) throws IOException;

	public Response deleteProfilePic(String id, String verifiedEmail);

	public Response updateProfilePic(MultipartFile file, String id, String decode);

}
