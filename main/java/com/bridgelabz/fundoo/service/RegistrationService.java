package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
import com.bridgelabz.fundoo.util.Util;

@Service
@PropertySource("classpath:message.properties")
public class RegistrationService {
	@Autowired
	IRegistrationRepository registrationrepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BCryptPasswordEncoder bcryprtpasswordEncoder;
	@Autowired
	Util util;
//	@Autowired 
//	Environment environment; 

//	@Autowired
//	RegistrationDto registrationDto;
//	@Autowired
//	RegistrationModel registrationModel;

	String TOKEN_SECRET = "forgotpassword";

	public String add(RegistrationDto registrationDto) {

		RegistrationModel registrationModel = modelMapper.map(registrationDto, RegistrationModel.class);
		registrationModel.setPassWord(bcryprtpasswordEncoder.encode(registrationDto.getPassWord()));
		String userEmail = registrationDto.getEmail();
		String token = util.encode(userEmail);
		util.sendMail(userEmail, token);

		registrationrepository.save(registrationModel);
		// return environment.getProperty("failureStatus");
		return "registration sucessfull";

	}

	public void delete(String usernmae) {
		registrationrepository.deleteByUserName(usernmae);

	}

	public void update(String email, String username) {
		// RegistrationModel model=registrationrepository.findByEmai(email);
		// model.setUserName(username);
		// registrationrepository.save(model);
	}

	public RegistrationModel getName(String email) {
		RegistrationModel registrationModel = registrationrepository.findByEmail(email);
		String name = registrationModel.getUserName();
		System.out.println("name is :" + name);
		return registrationrepository.findByEmail(email);

	}

	public String getNumber(String username) {
		RegistrationModel registrationModel = registrationrepository.findByUserName(username);
		String number = registrationModel.getMobileNumber();
		return number;
	}

	public RegistrationModel getData(String email, String username) {
		RegistrationModel registrationModel = registrationrepository.findByEmail(email);
		registrationModel.setUserName(username);
		registrationModel = modelMapper.map(registrationModel, RegistrationModel.class);
		registrationrepository.save(registrationModel);
		return registrationModel;
	}

	public boolean verify(LoginDto loginDto) {
		String email = loginDto.getEmail();
		String password = loginDto.getPassword();
		RegistrationModel registrationModel = registrationrepository.findByUserName(email);
		if (registrationModel.getPassWord().equals(password))
			return true;
		else
			return false;
	}

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

	public boolean resetPassword(String decodedstring, String password) {
		RegistrationModel registrationModel = registrationrepository.findByEmail(decodedstring);
		System.out.println("found use by email :" + registrationModel);
		registrationModel.setPassWord(password);
		registrationrepository.save(registrationModel);
		return true;
	}

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
