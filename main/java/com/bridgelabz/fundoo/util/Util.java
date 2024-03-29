package com.bridgelabz.fundoo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.bridgelabz.fundoo.model.EmailDataModel;
import com.bridgelabz.fundoo.response.Response;
//@Cacheable
public class Util {
	String TOKEN_SECRET = "forgotpassword";
	@Autowired(required = true)
	private JavaMailSender javaMailSender;
	@Autowired
	Environment environment;

	public String encode(String email) {
		String token = "";

		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

			token = JWT.create().withClaim("emailId", email).sign(algorithm);

		} catch (Exception e) {
			System.out.println("Unable to create JWT Token");
		}
		return token;

	}
	//@Cacheable(value = "note", key = "#emailDataModel")
	public Response sendMail(EmailDataModel emailDataModel) {
		try {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailDataModel.getEmailId());
		mail.setSubject("Verification Code:");
		mail.setText(emailDataModel.getToken());
		javaMailSender.send(mail);
		return new Response(200, null, environment.getProperty("mailsent"));
		}catch(Exception e)
		{
			return new Response(200, null, environment.getProperty("mailnotsent"));
		}

	}

	public String decode(String token) {


		Verification verification = null;
		try {
			verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		}
		com.auth0.jwt.JWTVerifier jwtverifier = verification.build();

		DecodedJWT decodedjwt = jwtverifier.verify(token);
		System.out.println("token :" + token);

		Claim claim = decodedjwt.getClaim("emailId");
		if (claim == null)
			return null;

		return claim.asString();
	}

}
