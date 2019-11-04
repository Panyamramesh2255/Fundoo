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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.RegistrationDto;
import com.bridgelabz.fundoo.exception.RegistrationException;
import com.bridgelabz.fundoo.model.RegistrationModel;
import com.bridgelabz.fundoo.repository.IRegistrationRepository;
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
	Util tokenUtil;

	@Mock
	RegistrationDto regDTO;

	@Mock
	JavaMailSender javaMailSender;

	@Mock
	ModelMapper modelmapper;

	@Mock
	PasswordEncoder passwordEncoder;

	@Mock
	LoginDto userDTO;

	RegistrationModel user = new RegistrationModel("1","ramesh", "123456", false, "panyamramesh2255@gmail.com","9878987867",null);
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
		regDTO.setEmail("panyamramesh2255@gmail.com");
		System.out.println("email setting after regdto "+regDTO.getEmail());
		regDTO.setPassWord("123456");
		regDTO.setMobileNumber("9878987867");
		regDTO.setUserName("ramesh");
		Optional<RegistrationModel> already = Optional.of(user);
		when(userRepository.findByEmail(regDTO.getEmail())!=null)
				.thenThrow(new RegistrationException("email is already registered"));
		when(modelmapper.map(regDTO, RegistrationModel.class)).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(regDTO.getEmail(), already.get().getEmail());
	}

	/**
	 * to test the login api
	 */
	@Test
	public void loginTest() {

		userDTO.setEmail("panyamramesh2255@gmail.com");
		userDTO.setPassword("12345678");
		Optional<RegistrationModel> already = Optional.of(user);
		when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
		when(passwordEncoder.matches(userDTO.getPassword(), user.getPassWord())).thenReturn(true);
		assertEquals(userDTO.getEmail(), already.get().getEmail());
	}

	/**
	 * to test forgot password api
	 */
	@Test
	public void forgotPassword() {
		when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(userDTO.getEmail(), user.getEmail());
	}

	/**
	 * to test reset password
	 */
	@Test
	public void resetPassword() {
		user.setPassWord("123456");
		user.setPassWord(passwordEncoder.encode(user.getPassWord()));
		when(userRepository.save(user)).thenReturn(user);
	}

}
