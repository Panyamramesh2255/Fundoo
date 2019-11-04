package com.bridgelabz.fundoo.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class WebappTest extends RegistrationApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	@Before
	public void setup()
	{
		mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void craeatingNote() throws Exception
	{
		mockMvc.perform(post("/note"))
		.andExpect(status().isOk())
		.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect((ResultMatcher) jsonPath(("$.setTitle").valueOf("test"))).andExpect((ResultMatcher) jsonPath(("$.setdescription").valueOf("should complete tasks of the day first..")))
	    .andExpect((ResultMatcher) jsonPath(("$.setemail").valueOf("rampanyam123@gmail.com"))).
	    andExpect((ResultMatcher) containsString("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbElkIjoicmFtcGFueWFtMTIzQGdtYWlsLmNvbSJ9.XhTyVV81Sfp1wovhVR3swsJ9owewqff5EdCZ2h-I8VU")).andExpect(status().isOk());
	  
		
	}
	

}
