package com.bridgelabz.fundoo.configuration;



import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bridgelabz.fundoo.model.RegistrationModel;
import com.bridgelabz.fundoo.note.model.LableModel;
import com.bridgelabz.fundoo.repository.IRegistrationRepository;
import com.bridgelabz.fundoo.util.Util;
@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public BCryptPasswordEncoder bcryprtpasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public Util util()
	{
		return new Util();
	}
	public LableModel lableModel()
	{
		return new LableModel();
	}
}
