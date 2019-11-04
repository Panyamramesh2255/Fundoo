package com.bridgelabz.fundoo.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bridgelabz.fundoo.RegistrationApplication;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.note.service.NoteService;
import com.bridgelabz.fundoo.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationApplication.class)
public class RegistrationApplicationTests {
	@Autowired
	private NoteService noteService;
	@Autowired
	private NoteRepository noteRepository;
	private MockMvc mockMvc;

	
//	@Test
//	public void NoteCreation()
//	{
//		NoteDto notedto=new NoteDto("task","should complete tasks of the day first.","rampanyam123@gmail.com");
//	    String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbElkIjoicmFtcGFueWFtMTIzQGdtYWlsLmNvbSJ9.XhTyVV81Sfp1wovhVR3swsJ9owewqff5EdCZ2h-I8VU";
//		Response response=new Response(200, null, "success");
//	    when(noteService.createNote(notedto, token)).thenReturn(response);
//	}
//	@Test
//	public void createNote() throws Exception
//	{
//		Response response=new Response(200, null, "success");
//		mockMvc.perform(post("/note"))
//		.andExpect(status().isOk());
//		
//	}

}
