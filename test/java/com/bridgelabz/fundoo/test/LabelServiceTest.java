package com.bridgelabz.fundoo.test;

	import static org.junit.Assert.assertEquals;
	import static org.mockito.Mockito.when;

	import java.util.List;
	import java.util.Optional;

	import org.junit.Before;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.modelmapper.ModelMapper;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.test.context.junit4.SpringRunner;
	import org.springframework.test.web.servlet.MockMvc;
	import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.fundoo.note.dto.LableDto;
import com.bridgelabz.fundoo.note.model.LableModel;
import com.bridgelabz.fundoo.note.repository.LabelRepository;
import com.bridgelabz.fundoo.note.service.LableService;

	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class LabelServiceTest {
	private MockMvc mockmvc;

	@InjectMocks
	LableService labelImpl;

	@Mock
	LabelRepository labelRepository;

	@Mock
	LableDto labelDTO;

	@Mock
	ModelMapper modelMapper;

	LableModel label = new LableModel("5dbc1b1c3f43762b578e7390", "pratikshatamadalge21@gmail,com","Task", null);

	@Before
	public void Setup() throws Exception {
	mockmvc = MockMvcBuilders.standaloneSetup(labelImpl).build();
	}

	/**
	* Test case for create label api
	*/
	@Test
	public void createLabelTest() {
	labelDTO.setLableName("Junit");
	// Optional<Label> already = Optional.of(label);
	when(modelMapper.map(labelDTO, LableModel.class)).thenReturn(label);
	when(labelRepository.save(label)).thenReturn(label);
	}

	/**
	* Test case for update label api
	*/
	@Test
	public void updateLabelTest() {
	// Optional<Label> already = Optional.of(label);
	label.setLableName("7 wonders in world....");
	when(labelRepository.save(label)).thenReturn(label);
	}

	/**
	* Test case for delete label api
	*/
	@Test
	public void deleteLabelTest() {

	List<LableModel> label1 = null;
	String labelId = "5dba69b03f43761e31622cbe";
	String emailId = "pratikshatamadalge21@gmail.com";
	// Optional<Label> already = Optional.of(label);
	when(labelRepository.findByEmail(emailId)).thenReturn(label1);
	labelRepository.deleteById(labelId);

	}

	/**
	* Test case to fetch all label api
	*/
	@Test
	public void getLabelTest() {
	List<LableModel> label1 = null;
	String emailId = "pratikshatamadalge21@gmail.com";
	Optional<LableModel> already = Optional.of(label);
	when(labelRepository.findByEmail(emailId)).thenReturn(label1);
	assertEquals(label.getEmail(), already.get().getEmail());
	}


}
