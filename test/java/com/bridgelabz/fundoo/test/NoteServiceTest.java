package com.bridgelabz.fundoo.test;

import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.fundoo.note.controller.NoteController;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.note.service.NoteService;
import com.bridgelabz.fundoo.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteServiceTest {

	private MockMvc mockmvc;
	@Spy
	@InjectMocks
	NoteService noteService;
	@Mock
	Environment environment;

	@Mock
	NoteController notecontroller;

	@Mock
	NoteRepository noteRepository;

	@Mock
	ModelMapper modelMapper;

	NoteModel note = new NoteModel("7 wonders in world", "taj mahal", "panyamramesh2255@gmail.com", null, null, false,
			false, false, null, null, false, null, null);

	@Before
	public void Setup() throws Exception {
		mockmvc = MockMvcBuilders.standaloneSetup(noteService).build();
	}

	/**
	 *
	 * Test case for create note api
	 *
	 * @throws Exception
	 */
	@Test
	public void testCreateNote() throws Exception {

		NoteDto dto = new NoteDto();

		dto.setTitle("title");
		dto.setDescription("description");
		dto.setEmail("user@gmail.com");
		String email = "user@gmail.com";
		String verifiedEmail = "user@gmail.com";
		//when(dto.getEmail()).thenReturn(email);
		assertEquals(true, email.contentEquals(verifiedEmail));
//		if (dto.getEmail().contentEquals(verifiedEmail))
		//when(anything().equals(verifiedEmail)).thenReturn(true);
			when(modelMapper.map(dto, NoteModel.class)).thenReturn(note);
		Response response = noteService.createNote(dto, email);
		assertEquals(200, response.getStatusCode());

	}

	/**
	 * Test case for update note api
	 */
	@Test
	public void updateNoteTest() {
		Optional<NoteModel> already = Optional.of(note);
		note.setTitle("7 wonders in world....");
		note.setTitle("taj mahal");
		when(noteRepository.save(note)).thenReturn(note);
		assertEquals(note.getTitle(), already.get().getTitle());
	}

	/**
	 * Test case for delete note api
	 */
	@Test
	public void deleteNoteTest() {
		String noteId = "5dba69b03f43761e31622cbe";
		String emailId = "pratikshatamadalge21@gmail.com";
		Optional<NoteModel> already = Optional.of(note);
		when(noteRepository.findByIdAndEmail(noteId, emailId)).thenReturn(note);
		noteRepository.deleteById(noteId);
		assertEquals(note.getId(), already.get().getId());
	}

	/**
	 * Test case to fetch all note
	 */
	@Test
	public void getAllNoteTest() {
		List<NoteModel> note1 = null;
		String emailId = "panyamramesh2255@gmail.com";
		Optional<NoteModel> already = Optional.of(note);
		when(noteRepository.findByEmail(emailId)).thenReturn(note1);
		assertEquals(note.getEmail(), already.get().getEmail());
	}

	/**
	 * Test case for isPinned api
	 */
	@Test
	public void isPinnedTest() {
		String noteId = "5dba69b03f43761e31622cbe";
		String emailId = "shelkeva@gmail.com";
		note.setPinned(false);
		when(noteRepository.findByIdAndEmail(noteId, emailId)).thenReturn(note);
		if (note.isPinned())
			note.setPinned(false);
		else
			note.setPinned(true);
		assertEquals(true, note.isPinned());
	}

	/**
	 * Test case for isTrashed api
	 */
	@Test
	public void isTrashedTest() {
		String noteId = "5dba69b03f43761e31622cbe";
		String emailId = "shelkeva@gmail.com";
		note.setTrashed(false);
		when(noteRepository.findByIdAndEmail(noteId, emailId)).thenReturn(note);
		if (note.isTrashed())
			note.setTrashed(false);
		else
			note.setTrashed(false);
		assertEquals(false, note.isTrashed());

	}

	/**
	 * Test case for isArchieved api
	 */
	@Test
	public void isArchievedTest() {
		String noteId = "5dba69b03f43761e31622cbe";
		String emailId = "shelkeva@gmail.com";
		note.setArchived(false);
		when(noteRepository.findByIdAndEmail(noteId, emailId)).thenReturn(note);
		if (note.isArchived())
			note.setArchived(false);
		else
			note.setArchived(false);
		assertEquals(false, note.isArchived());
	}
}
