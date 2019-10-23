package com.bridgelabz.fundoo.note.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.note.model.LableModel;
import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.note.repository.LabelRepository;
import com.bridgelabz.fundoo.note.repository.NoteRepository;
import com.bridgelabz.fundoo.response.Response;
@Service
public class LableService implements ILable {
	@Autowired
	LabelRepository lableRepository;
	@Autowired
	NoteRepository noteRepository;
	

	/**
	 *
	 */
	@Override
	public Response craeteLabel(String lableName, String email) {
		LableModel lableModel=new LableModel();
		lableModel.setEmail(email);
		lableModel.setLableName(lableName);
     lableRepository.save(lableModel);        
		return new Response(200, null, "lable created...");
	}

	@Override
	public String getAllNotes(String token, String lableId) {

		return null;
	}

	@Override
	public String updateNote(String token, String lableName) {

		return null;
	}

	@Override
	public Response deleteNote(String token, String lableId) {

		return new Response(200, null, "note deleted..");
	}

	public Response addingNote(String noteid, String lableid) {
		LableModel lableModel=lableRepository.findById(lableid).get();
		//NoteModel noteModel=new NoteModel();
		System.out.println(lableModel);
		NoteModel notemodel=noteRepository.findById(noteid).get();
		//notemodel.setLableList();
		System.out.println(notemodel);
		lableModel.getNoteList().add(notemodel);
		lableRepository.save(lableModel);
		notemodel.getLableList().add(lableModel);
		noteRepository.save(notemodel);
		return new Response(200, null, "Note added sucessfully..");
	}

	
	@Override
	public Response deleteNoteList(String lableid, String noteid) {
		LableModel lableModel=lableRepository.findById(lableid).get();
	    NoteModel notemodel=noteRepository.findById(noteid).get();
	   // lableModel.d
		
		
		return new Response(200, null, "deleted from list..");
	}

	public List<NoteModel> sortnoteByTitle() {
		List<NoteModel> notemodelList=noteRepository.findAll();
		//System.out.println(notemodelList);
		notemodelList.sort((NoteModel n1,NoteModel n2)->n1.getTitle().compareTo(n2.getTitle()));
		return notemodelList;
		
	}

	public List<NoteModel> sortbyUpdatedDate() {
		List<NoteModel> notemodelList=noteRepository.findAll();
		//System.out.println(notemodelList);
		notemodelList.sort((NoteModel n1,NoteModel n2)->n1.getEditedAt().compareTo(n2.getEditedAt()));
		return notemodelList;
	}

}
