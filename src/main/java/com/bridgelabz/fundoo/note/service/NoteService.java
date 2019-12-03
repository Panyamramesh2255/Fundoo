package com.bridgelabz.fundoo.note.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.exception.NoteException;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.note.util.ENUM;
import com.bridgelabz.fundoo.repository.IRegistrationRepository;
import com.bridgelabz.fundoo.response.Response;
/**
 * 
 * @author PanyamRamesh
 * purpose: NoteService Class
 *
 */
@Service
@PropertySource("classpath:message1.properties")

public class NoteService implements Inote {
	@Autowired
	private Environment environment;
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	IRegistrationRepository registrationRepository;

	/**
	 * purpose: creating Note
	 */
	@Override
	public Response createNote(NoteDto noteDto, String verifiedEmail) {
		String userEmail = noteDto.getEmail();
		if (verifiedEmail.equals(userEmail)) {
			NoteModel noteModel = modelMapper.map(noteDto, NoteModel.class);
			noteModel.setCreatedAt(LocalDate.now());
			noteModel.setEditedAt(LocalDate.now());
			noteModel.setActive(true);
			noteRepository.save(noteModel);

			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose: Updating Note
	 */
	@Override
	public Response updateNote(String id, String title, String description, String verifiedEmail) {
		NoteModel noteModel = noteRepository.findById(id).get();
		if (noteModel == null)
			throw new NoteException("Note not found or invalid note details! ");
		String userEmail = noteModel.getEmail();
		if (userEmail.contentEquals(verifiedEmail) == true) {
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
	
	public Response deleteNote(String id, String verifiedEmail) {
		NoteModel note = noteRepository.findById(id).get();
		String userEmail = note.getEmail();
		if (userEmail.contentEquals(verifiedEmail) == true && note.isTrashed()) {
			noteRepository.deleteById(id);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("nottrashed"));
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
	public Response pin(String id, String verifiedEmail) {
		NoteModel noteModel = noteRepository.findById(id).get();
		String userEmail = noteModel.getEmail();
		if (userEmail.contentEquals(verifiedEmail) == true) {
			noteModel.setPinned(!noteModel.isPinned());
			noteRepository.save(noteModel);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose: Un pin Note
	 */

	/**
	 * purpose: Archive Note
	 */
	@Override
	public Response archive(String id, String verifiedEmail) {
		NoteModel noteModel = noteRepository.findById(id).get();
		String userEmail = noteModel.getEmail();
		if (userEmail.contentEquals(verifiedEmail) == true) {
			noteModel.setArchived(!noteModel.isArchived());
			noteRepository.save(noteModel);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose: UnArchive Note
	 */
	@Override
	public Response unArchive(String id, String verifiedEmail) {
		NoteModel noteModel = noteRepository.findById(id).get();
		if (noteModel == null)
			throw new NoteException("invalid note details! ");
		String userEmail = noteModel.getEmail();
		if (userEmail.contentEquals(verifiedEmail) == true) {

			noteModel.setArchived(!noteModel.isArchived());
			noteRepository.save(noteModel);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose:Trash Note
	 */
	@Override
	public Response trash(String id, String verifiedEmail) {
		NoteModel noteModel = noteRepository.findById(id).get();
		System.out.println("note model.."+noteModel);
		if (noteModel == null)
			throw new NoteException("invalid note details! ");
		String userEmail = noteModel.getEmail();
		System.out.println("user email.."+userEmail);
        System.out.println("verified email.."+verifiedEmail);
		if (userEmail.contentEquals(verifiedEmail)) {
                  System.out.println("user match found...");
			if (noteModel.isTrashed())
				return new Response(200, null, environment.getProperty("failurestatus"));
         System.out.println("note is not yet trashed..");
			noteModel.setTrashed(true);
			noteRepository.save(noteModel);
			return new Response(200, null, environment.getProperty("successstatus"));
		}
		System.out.println("user match not found...");
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * Restore Note
	 */
	@Override
	public Response reStore(String id, String verifiedEmail) {

		NoteModel noteModel = noteRepository.findById(id).get();
		if (noteModel == null)
			throw new NoteException("invalid note details! ");
		String userEmail = noteModel.getEmail();

		if (userEmail.contentEquals(verifiedEmail) == true) {
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
	public Response addTOCollaborator(String id, String verifiedEmail) {
		String email=noteRepository.findById(id).get().getEmail();
		if(email.contentEquals(verifiedEmail))
		{
		NoteModel note = noteRepository.findById(id).get();
		if (note == null)
			throw new NoteException("invalid note details! ");
		if (note != null) {
			note.getCollaborator().add(email);
			noteRepository.save(note);
			return new Response(200, null, environment.getProperty("collabsucess"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));

	}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 * purpose: Adding Reminder
	 */
	@Override
	public Response addReminder(LocalDateTime reminder, ENUM repeat, String id, String verifiedEmail) {
		NoteModel note = noteRepository.findById(id).get();
		if (note == null)
			throw new NoteException("invalid note details! ");
		String userEmail = note.getEmail();
		if (userEmail.contentEquals(verifiedEmail) == true && reminder.compareTo(LocalDateTime.now()) > 0) {
			note.setReminder(reminder);
			note.setRepeat(repeat);
			noteRepository.save(note);
			return new Response(200, null, environment.getProperty("remindersuccess"));
		}
		return new Response(400, null, environment.getProperty("reminderfailure"));
	}

	/**
	 * purpose: Updating reminder
	 */
	@Override
	public Response updateReminder(LocalDateTime reminder, ENUM repeat, String id, String verifiedEmail) {
		NoteModel note = noteRepository.findById(id).get();
		if (note == null)
			throw new NoteException("invalid note details! ");
		String userEmail = note.getEmail();
		if (userEmail.contentEquals(verifiedEmail) == true && reminder.compareTo(LocalDateTime.now()) > 0) {
			note.setReminder(reminder);
			noteRepository.save(note);
			return new Response(200, null, environment.getProperty("rupdatesu"));

		}
		return new Response(400, null, environment.getProperty("rupadtedfa"));
	}

	@Override
	public Response deleteReminder(String id, String verifiedEmail) {
		NoteModel note = noteRepository.findById(id).get();
		if (note == null)
			throw new NoteException("invalid note details! ");
		String userEmail = note.getEmail();
		if (userEmail.contentEquals(verifiedEmail) == true) {

			note.setReminder(null);;
			noteRepository.save(note);
			return new Response(200, null, environment.getProperty("rdeletes"));
		}
		return new Response(400, null, environment.getProperty("rdeletef"));
	}

}
