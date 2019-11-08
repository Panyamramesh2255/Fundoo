package com.bridgelabz.fundoo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.LoginException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDto;
import com.bridgelabz.fundoo.exception.RegistrationException;
import com.bridgelabz.fundoo.model.EmailDataModel;
import com.bridgelabz.fundoo.model.RegistrationModel;
import com.bridgelabz.fundoo.note.service.NoteService;
import com.bridgelabz.fundoo.repository.IRegistrationRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.util.ConstantClass;
import com.bridgelabz.fundoo.util.Util;


@Service
@PropertySource("classpath:message.properties")
public class RegistrationService implements IRegistrationService {
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
	@Autowired
	private RabbitTemplate rabbitTemplate;

	String TOKEN_SECRET = "forgotpassword";

	/**
	 * purpose: Registration
	 * 
	 * @param registrationDto
	 * @return
	 */
	
	private static final Logger LOG = LoggerFactory.getLogger(NoteService.class);

	
	public Response add(RegistrationDto registrationDto) {

		LOG.info(ConstantClass.SERVICE_REGISTER_USER);
		EmailDataModel emailDataModel = new EmailDataModel();
		RegistrationModel registrationModel = modelMapper.map(registrationDto, RegistrationModel.class);

		if (registrationModel == null)
			throw new RegistrationException(ConstantClass.NOTEXIST);
		registrationModel.setPassWord(bcryprtpasswordEncoder.encode(registrationDto.getPassWord()));
		String userEmail = registrationDto.getEmail();
		String token = util.encode(userEmail);

		emailDataModel.setEmailId(userEmail);
		emailDataModel.setToken(token);
		emailDataModel.setMailMessage("Verification mail");

		rabbitTemplate.convertAndSend("key", emailDataModel);

		// util.sendMail(userEmail, token);

		registrationrepository.save(registrationModel);

		return new Response(200, null, environment.getProperty("Registrationsuccess"));

	}

	/**
	 * purpose: Deleting user
	 * 
	 * @param usernmae
	 */
	public Response delete(String usernmae) {
		registrationrepository.deleteByUserName(usernmae);
		return new Response(200, null, environment.getProperty("successstatus"));

	}

	/**
	 * purpose: updating user_name
	 * 
	 * @param email
	 * @param username
	 */
	public Response update(String email, String username) {
		RegistrationModel model = registrationrepository.findByEmail(email);
		model.setUserName(username);
		registrationrepository.save(model);
		return new Response(200, null, environment.getProperty("successstatus"));
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
	 * purpose: Updating user name
	 * 
	 * @param email
	 * @param username
	 * @return
	 */
	public Response getData(String email, String username) {
		RegistrationModel registrationModel = registrationrepository.findByEmail(email);
		registrationModel.setUserName(username);
		registrationrepository.save(registrationModel);
		return new Response(200, null, environment.getProperty("successstatus"));
	}

	/**
	 * purpose: Login method
	 * 
	 * @param loginDto
	 * @return
	 * @throws LoginException
	 */
	public Response verify(String email, String password) throws LoginException {

		System.out.println("user psssword.. " + password);
		RegistrationModel registrationModel = registrationrepository.findByEmail(email);
		if (registrationModel == null)
			throw new LoginException(ConstantClass.NOTEXIST);
		if (bcryprtpasswordEncoder.matches(password, registrationModel.getPassWord())) {

			System.out.println("registration successfull..");
			return new Response(200, null, environment.getProperty("Loginsuccess"));
		}

		return new Response(400, null, environment.getProperty("Loginfailure"));
	}

	/**
	 * purpose: Sending Email
	 */
	@Autowired(required = true)
	private JavaMailSender javaMailSender;

	public Response sendEmail(String to, String subject, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);
		javaMailSender.send(message);
		return new Response(200, null, environment.getProperty("mailsucess"));

	}

	/**
	 * purpose: Resetting Password
	 * 
	 * @param decodedstring
	 * @param password
	 * @return
	 */
	public Response resetPassword(String decodedstring, String password) {
		RegistrationModel registrationModel = registrationrepository.findByEmail(decodedstring);
		if (registrationModel != null) {
			System.out.println("found use by email :" + registrationModel);
			registrationModel.setPassWord(password);
			registrationrepository.save(registrationModel);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose: Verifying
	 * 
	 * @param token
	 * @return
	 */
	public Response verifying(String email) {
		System.out.println("decoded token is : " + email);
		RegistrationModel model = registrationrepository.findByEmail(email);
		System.out.println(model);
		if (model.getEmail().contentEquals(email)) {
			model.setVerify(true);
			registrationrepository.save(model);
			return new Response(200, null, environment.getProperty("sucessstatus"));
		}

		model.setVerify(false);
		return new Response(200, null, environment.getProperty("sucessstatus"));

	}

	public Response addProfilePic(MultipartFile file, String id, String verifiedEmail) throws IOException {
		try {
			byte[] b = file.getBytes();

			String location = "/home/bridgeit/Documents/ProfilePic";
			String filename = file.getName();
			java.nio.file.Path path = Paths.get(location + filename + "." + "jpg");
			Files.write(path, b);
			RegistrationModel user = registrationrepository.findById(id).get();
			user.setProfilepic(location + filename);
			registrationrepository.save(user);

		}

		catch (Exception e) {
			throw new RegistrationException("finding exception in thereding byte file..");
		}

		return new Response(200, null, ConstantClass.SUCCESSFULL);
	}

	public Response deleteProfilePic(String id, String verifiedEmail) {
		String userEmail = registrationrepository.findById(id).get().getEmail();
		System.out.println("user email is..  " + userEmail);
		if (userEmail.contentEquals(verifiedEmail)) {
			RegistrationModel user = registrationrepository.findById(id).get();
			user.setProfilepic(null);
			registrationrepository.save(user);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	public Response updateProfilePic(MultipartFile file, String id, String decode) {

		try {
			byte[] b = file.getBytes();

			String location = "/home/bridgeit/Documents/ProfilePic";
			String filename = file.getName();
			java.nio.file.Path path = Paths.get(location + filename + "." + "jpg");
			Files.write(path, b);
			RegistrationModel user = registrationrepository.findById(id).get();
			user.setProfilepic(location + filename);
			registrationrepository.save(user);

		}

		catch (Exception e) {
			throw new RegistrationException("finding exception in thereding byte file..");
		}

		return new Response(200, null, environment.getProperty("successstatus"));

	}

	public Response forgotPassword(String userEmail, String token) {
		EmailDataModel emailDataModel = new EmailDataModel();
		emailDataModel.setEmailId(userEmail);
		emailDataModel.setToken(token);
		emailDataModel.setMailMessage("Verification mail");

		rabbitTemplate.convertAndSend("key", emailDataModel);

		return new Response(200, null, environment.getProperty("mailsent"));
	}
}
