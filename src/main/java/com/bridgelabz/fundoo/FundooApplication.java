package com.bridgelabz.fundoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.bridgelabz.fundoo")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
/**
 * 
 * @author PanyamRamesh
 * 
 *
 */
public class FundooApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundooApplication.class, args);

	}

}
