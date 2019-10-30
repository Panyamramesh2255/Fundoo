package com.bridgelabz.fundoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@ComponentScan(basePackages = {"com.bridgelabz.fundoonote","com.bridgelabz.fundoo"})
//@EnableMongoRepositories(basePackages = {"com.bridgelabz.fundoo.repository,com.bridgelabz.fundoo.note.repository"})
@ComponentScan("com.bridgelabz.fundoo")
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})

public class RegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	
	}

}
