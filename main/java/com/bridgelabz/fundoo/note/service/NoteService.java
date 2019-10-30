package com.bridgelabz.fundoo.note.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.util.Util;

@Service
@PropertySource("classpath:status.properties")
public class NoteService implements Inote {
	@Autowired
	private Environment environment;
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private Util util;
	@Autowired
	NoteModel noteModel;

	/**
	 * purpose: creating Note
	 */
	@Override
	public Response createNote(NoteDto noteDto, String token) {
		String userEmail = noteDto.getEmail();
		String verifyingEmail = util.decode(token);
		if (verifyingEmail.contentEquals(userEmail) == true) {
			NoteModel noteModel = modelMapper.map(noteDto, NoteModel.class);
			noteModel.setCreatedAt(LocalDate.now());
			noteModel.setEditedAt(LocalDate.now());
			noteModel.setActive(true);
			noteRepository.save(noteModel);

			return new Response(200, null, environment.getProperty("sucesssstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose: Updating Note
	 */
	@Override
	public Response updateNote(String id, String title, String description, String token) {
		NoteModel noteModel = noteRepository.findById(id).get();
		String userEmail = noteModel.getEmail();
		String verifyingEmail = util.decode(token);
		if (userEmail.contentEquals(verifyingEmail) == true) {
			if (!title.isEmpty())
				noteModel.setTitle(title);
			if (!description.isEmpty())
				noteModel.setDescription(description);
			noteRepository.save(noteModel);

			return new Response(200, null, environment.getProperty("sucesssstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose: Updating Note
	 */
	@Override
	public Response deleteNote(String id, String token) {
		NoteModel note = noteRepository.findById(id).get();
		String userEmail = note.getEmail();
		String verifyingEMail = util.decode(token);
		if (userEmail.contentEquals(verifyingEMail) == true) {
			noteRepository.deleteById(id);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose: Getting All Notes
	 */
	@Override
	public List<NoteModel> getAllNotes() {
		List<NoteModel> list = noteRepository.findAll();
		return list;
	}

	/**
	 * purpose:Pin Note
	 */
	@Override
	public Response pin(String id, String token) {
		NoteModel noteModel = noteRepository.findById(id).get();
		String userEmail = noteModel.getEmail();
		String verifyingEmail = util.decode(token);
		if (userEmail.contentEquals(verifyingEmail) == true) {
			noteModel.setPinned(true);
			noteRepository.save(noteModel);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose: Unpin Note
	 */
	@Override
	public Response unPin(String id, String token) {
		NoteModel noteModel = noteRepository.findById(id).get();
		String userEmail = noteModel.getEmail();
		String verifyingEmail = util.decode(token);
		if (userEmail.contentEquals(verifyingEmail) == true) {
			boolean b = noteModel.isPinned();
			if (b == false)
				return new Response(200, null, environment.getProperty("failurestatus"));
			else
				noteModel.setPinned(false);
			noteRepository.save(noteModel);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose: Archive Note
	 */
	@Override
	public Response archive(String id, String token) {
		NoteModel noteModel = noteRepository.findById(id).get();
		String userEmail = noteModel.getEmail();
		String verifyingEmail = util.decode(token);
		if (userEmail.contentEquals(verifyingEmail) == true) {
			boolean b = noteModel.isArchived();
			if (b == true)
				return new Response(200, null, environment.getProperty("failurestatus"));
			else
				noteModel.setArchived(true);
			noteRepository.save(noteModel);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose: UnArchive Note
	 */
	@Override
	public Response unArchive(String id, String token) {
		NoteModel noteModel = noteRepository.findById(id).get();
		String userEmail = noteModel.getEmail();
		String verifyingEmail = util.decode(token);
		if (userEmail.contentEquals(verifyingEmail) == true) {
			boolean b = noteModel.isArchived();
			if (b == false)
				return new Response(200, null, environment.getProperty("failurestatus"));
			else
				noteModel.setArchived(false);
			noteRepository.save(noteModel);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose:Trash Note
	 */
	@Override
	public Response trash(String id, String token) {
		NoteModel noteModel = noteRepository.findById(id).get();
		String userEmail = noteModel.getEmail();
		String verifyingEmail = util.decode(token);
		if (userEmail.contentEquals(verifyingEmail) == true) {
			boolean response = noteModel.isTrashed();
			if (response == true)
				return new Response(200, null, environment.getProperty("failurestatus"));

			noteModel.setTrashed(true);
			noteRepository.save(noteModel);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * Restore Note
	 */
	@Override
	public Response reStore(String id, String token) {

		NoteModel noteModel = noteRepository.findById(id).get();
		String userEmail = noteModel.getEmail();
		String verifyingEmail = util.decode(token);
		if (userEmail.contentEquals(verifyingEmail) == true) {
			boolean response = noteModel.isTrashed();
			if (response == true) {
				noteModel.setTrashed(false);
				noteRepository.save(noteModel);
				return new Response(200, null, environment.getProperty("successstatus"));
			}
		}
		return new Response(400, null, environment.getProperty("failurestatus"));

	}

	/**
	 * purpose: Making collaboratorList
	 */
	public Response addTOCollaborator(String id, String email) {
		NoteModel note = noteRepository.findById(id).get();
		if (note != null) {
			note.getCollaborator().add(email);
			noteRepository.save(note);
			return new Response(200, null, environment.getProperty("sucesssstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));

	}

}
