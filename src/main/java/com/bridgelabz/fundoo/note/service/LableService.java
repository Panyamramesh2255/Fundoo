package com.bridgelabz.fundoo.note.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.exception.LabelException;
import com.bridgelabz.fundoo.exception.NoteException;
import com.bridgelabz.fundoo.note.model.LableModel;
import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.note.repository.LabelRepository;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.util.Util;

@PropertySource("classpath:message1.properties")
@Service
public class LableService implements ILable {
	@Autowired
	Environment environment;
	@Autowired
	LabelRepository lableRepository;
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	Util util;

	/**
	 * Creating a label
	 */
	@Override
	public Response craeteLabel(String lableName, String email, String token) {
		try {
			LableModel lableModel = new LableModel();
			lableModel.setEmail(email);
			lableModel.setLableName(lableName);
			lableRepository.save(lableModel);
			return new Response(200, null, environment.getProperty("sucesssstatus"));
		} catch (Exception e) {
			return new Response(200, null, environment.getProperty("failurestatus"));
		}
	}

	/**
	 * purpose:Getting AllNotes of a particular label
	 */
	@Override
	public List<NoteModel> getAllNotes(String lableId, String token) {

		LableModel lablemodel = lableRepository.findById(lableId).get();
		List<NoteModel> notelist = lablemodel.getNoteList();
		return notelist;

	}

	@Override
	public String updateNote(String token, String lableName) {

		return null;
	}

	/**
	 * Adding note to the label
	 */
	@Override
	public Response addingNote(String noteid, String lableid, String token) {
		LableModel lableModel = lableRepository.findById(lableid).get();
		if (lableModel == null)
			throw new LabelException("label not found or invalid label details");
		NoteModel notemodel = noteRepository.findById(noteid).get();
		if (notemodel == null)
			throw new NoteException("Note not found or invalid note details! ");
		String userEmail = notemodel.getEmail();
		String verifyingEmail = util.decode(token);
		if (verifyingEmail.contentEquals(userEmail) == true) {
			lableModel.getNoteList().add(notemodel);
			lableRepository.save(lableModel);
			notemodel.getLableList().add(lableModel);
			noteRepository.save(notemodel);

			return new Response(200, null, environment.getProperty("sucesssstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));
	}

	/**
	 *purpose: Deleting note from list
	 */
	@Override
	public Response deleteNoteFromList(String lableid, String noteid, String token) {
		LableModel lableModel = lableRepository.findById(lableid).get();
		if (lableModel == null)
			throw new LabelException("label not found or invalid label details");
		NoteModel notemodel = noteRepository.findById(noteid).get();
		if (notemodel == null)
			throw new NoteException("Note not found or invalid note details! ");

		String userEmail = notemodel.getEmail();
		String verifyingEmail = util.decode(token);
		if (verifyingEmail.contentEquals(userEmail) == true) {
			lableModel.getNoteList().remove(notemodel);
			return new Response(200, null, environment.getProperty("sucesssstatus"));
		}
		return new Response(400, null, environment.getProperty("failurestatus"));

	}

	/**
	 * purpose:Sorting list of notes by title
	 */
	@Override
	public List<NoteModel> sortnoteByTitle() {
		List<NoteModel> notemodelList = noteRepository.findAll();
		notemodelList.sort((NoteModel n1, NoteModel n2) -> n1.getTitle().compareTo(n2.getTitle()));
		return notemodelList;

	}

	/**
	 * purpose:Sorting list of notes by Updated Date
	 */
	@Override
	public List<NoteModel> sortbyUpdatedDate() {
		List<NoteModel> notemodelList = noteRepository.findAll();
		notemodelList.sort((NoteModel n1, NoteModel n2) -> n1.getEditedAt().compareTo(n2.getEditedAt()));
		return notemodelList;
	}

	public Response verifying() {

		return null;
	}

}
