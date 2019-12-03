package com.bridgelabz.fundoo.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDto;
import com.bridgelabz.fundoo.exception.RegistrationException;
import com.bridgelabz.fundoo.model.EmailDataModel;
import com.bridgelabz.fundoo.model.RegistrationModel;
import com.bridgelabz.fundoo.repository.IRegistrationRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.service.RegistrationService;
import com.bridgelabz.fundoo.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	private MockMvc mockmvc;

	@InjectMocks
	RegistrationService userServiceImpl;

	@Mock
	IRegistrationRepository userRepository;

	@Mock
	private Util tokenUtil;
	@Mock
	private Environment environment;

	@Mock
	private BCryptPasswordEncoder bcrypt;

	@Mock
	private JavaMailSender javaMailSender;

	@Mock
	private ModelMapper modelmapper;

	@Mock
	private PasswordEncoder passwordEncoder;
	@Mock
	private RabbitTemplate rabbitTemplate;

	RegistrationModel user = new RegistrationModel("1", "ramesh", "123456", false, "panyamramesh2255@gmail.com",
			"9878987867", null);

	@Before
	public void Setup() throws Exception {
		mockmvc = MockMvcBuilders.standaloneSetup(userServiceImpl).build();
	}

	/**
	 * to test register api
	 *
	 * @throws Exception
	 */
	@Test
	public void registerTest() throws Exception {
		RegistrationDto regDTO = new RegistrationDto();
		regDTO.setEmail("panyamramesh2255@gmail.com");
		System.out.println("email setting amethodCallfter regdto " + regDTO.getEmail());
		regDTO.setPassWord("123456");
		regDTO.setMobileNumber("9878987867");
		regDTO.setUserName("ramesh");
		Optional<RegistrationModel> already = Optional.of(user);
		when(userRepository.findByEmail(regDTO.getEmail()) != null)
				.thenThrow(new RegistrationException("email is already registered"));
		when(modelmapper.map(regDTO, RegistrationModel.class)).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(regDTO.getEmail(), already.get().getEmail());
	}

	/**
	 * to test the login api
	 * 
	 */
//	@Test
//	public void testLogin() {
//		try {
//			LoginDto loginDto= new LoginDto();
//			String email = "panyamramesh2255141@gmail.com";
//			String password = "123456";
//			RegistrationModel user = new RegistrationModel();
//			when(userRepository.findByEmail(email)).thenReturn(user);
//			when(bcrypt.matches(password, user.getPassWord())).thenReturn(true);
//			Response response = userServiceImpl.verify(loginDto);
//			assertEquals(200, response.getStatusCode());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * to test forgot password api
	 */
	@Test
	public void testforgotPassword() {

		EmailDataModel emailDataModel = new EmailDataModel();
		String mail = "letter2vijaykumarbhavanur@gmail.com";
		String token = "verification code..";
		emailDataModel.setEmailId(mail);
		emailDataModel.setToken(token);
		emailDataModel.setMailMessage("Verification mail");
		rabbitTemplate.convertAndSend("key", emailDataModel);
		Response response = userServiceImpl.forgotPassword(mail, token);
		assertEquals(200, response.getStatusCode());
	}

	/**
	 * to test reset password
	 */
	@Test
	public void testResetPassword() {
		RegistrationModel user = new RegistrationModel();
		String email = "panyamramesh2255@gmail.com";
		String password = "123456789";
		String key = "message";
		String value = "success";
		when(userRepository.findByEmail(email)).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);
		when(environment.getProperty(key)).thenReturn(value);
        Response response = userServiceImpl.resetPassword(email, password);
		assertEquals(200, response.getStatusCode());
	}

}
