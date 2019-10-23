package com.bridgelabz.fundoo.note.service;

import java.util.List;

import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.response.Response;

public interface ILable {
	public  Response craeteLabel(String lableName,String email);
	public List<NoteModel> getAllNotes(String lableId);
	public String updateNote(String token,String lableName);
	public List<NoteModel> sortnoteByTitle();
	public Response addingNote(String lableid,String noteid);
	public void deleteNoteFromList(String lableid,String noteid);
	public List<NoteModel> sortbyUpdatedDate();
}
