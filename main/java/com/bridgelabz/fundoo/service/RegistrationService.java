package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.LoginException;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDto;
import com.bridgelabz.fundoo.model.RegistrationModel;
import com.bridgelabz.fundoo.repository.IRegistrationRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.util.Util;

@Service
@PropertySource("classpath:message.properties")
public class RegistrationService {
	@Autowired
	private IRegistrationRepository registrationrepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BCryptPasswordEncoder bcryprtpasswordEncoder;
	@Autowired
	private Util util;
	@Autowired
	private Environment environment;
//	@Autowired 
//	Environment environment; 

//	@Autowired
//	RegistrationDto registrationDto;
//	@Autowired
//	RegistrationModel registrationModel;

	String TOKEN_SECRET = "forgotpassword";

	/**
	 * purpose: Registration
	 * 
	 * @param registrationDto
	 * @return
	 */
	public Response add(RegistrationDto registrationDto) {

		RegistrationModel registrationModel = modelMapper.map(registrationDto, RegistrationModel.class);
		registrationModel.setPassWord(bcryprtpasswordEncoder.encode(registrationDto.getPassWord()));
		String userEmail = registrationDto.getEmail();
		String token = util.encode(userEmail);
		util.sendMail(userEmail, token);

		registrationrepository.save(registrationModel);
		// return environment.getProperty("failureStatus");
		return new Response(200, null, environment.getProperty("successstatus"));

	}

	/**
	 * purpose: Deleting user
	 * 
	 * @param usernmae
	 */
	public void delete(String usernmae) {
		registrationrepository.deleteByUserName(usernmae);

	}

	public void update(String email, String username) {
		// RegistrationModel model=registrationrepository.findByEmai(email);
		// model.setUserName(username);
		// registrationrepository.save(model);
	}

	/**
	 * purpose: Getting Name..from user details
	 * 
	 * @param email
	 * @return
	 */
	public RegistrationModel getName(String email) {
		RegistrationModel registrationModel = registrationrepository.findByEmail(email);
		String name = registrationModel.getUserName();
		System.out.println("name is :" + name);
		return registrationrepository.findByEmail(email);

	}

	/**
	 * purpose: Getting number from user details
	 * 
	 * @param username
	 * @return
	 */
	public String getNumber(String username) {
		RegistrationModel registrationModel = registrationrepository.findByUserName(username);
		String number = registrationModel.getMobileNumber();
		return number;
	}

	/**
	 * purpose: Updating username
	 * 
	 * @param email
	 * @param username
	 * @return
	 */
	public RegistrationModel getData(String email, String username) {
		RegistrationModel registrationModel = registrationrepository.findByEmail(email);
		registrationModel.setUserName(username);
		registrationModel = modelMapper.map(registrationModel, RegistrationModel.class);
		registrationrepository.save(registrationModel);
		return registrationModel;
	}

	/**
	 * purpose: Login method
	 * 
	 * @param loginDto
	 * @return
	 * @throws LoginException
	 */
	public boolean verify(LoginDto loginDto) throws LoginException {
		String email = loginDto.getEmail();
		String password = loginDto.getPassword();
		RegistrationModel registrationModel = registrationrepository.findByUserName(email);
		if (registrationModel == null)
			throw new LoginException("user not found...!");
		if (registrationModel.getPassWord().equals(password))
			return true;
		else
			return false;
	}

	/**
	 * purpose: Sending Email
	 */
	@Autowired(required = true)
	private JavaMailSender javaMailSender;

	public void sendEmail(String to, String subject, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);
		javaMailSender.send(message);

	}

	/**
	 * purpose: Resetting Password
	 * 
	 * @param decodedstring
	 * @param password
	 * @return
	 */
	public boolean resetPassword(String decodedstring, String password) {
		RegistrationModel registrationModel = registrationrepository.findByEmail(decodedstring);
		System.out.println("found use by email :" + registrationModel);
		registrationModel.setPassWord(password);
		registrationrepository.save(registrationModel);
		return true;
	}

	/**
	 * purpose: Verifying
	 * 
	 * @param token
	 * @return
	 */
	public String verifying(String token) {
		String email = util.decode(token);
		System.out.println("decoded token is : " + email);
		RegistrationModel model = registrationrepository.findByEmail(email);
		System.out.println(model);
		if (model.getEmail().contentEquals(email)) {
			model.setVerify(true);
			registrationrepository.save(model);
			return "verified";
		}

		model.setVerify(false);
		return "Not verified!";

	}
}
