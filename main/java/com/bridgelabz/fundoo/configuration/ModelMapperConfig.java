package com.bridgelabz.fundoo.configuration;




import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bridgelabz.fundoo.note.model.LableModel;
import com.bridgelabz.fundoo.util.Util;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
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
    @Bean
    public Docket productApi() {

        return new Docket(DocumentationType.SWAGGER_2)

                .select() .apis(RequestHandlerSelectors.basePackage("com.bridgelabz.fundoo")) .build();

    }
}
