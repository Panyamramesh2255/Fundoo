package com.bridgelabz.fundoo.note.service;

import java.util.List;

import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.response.Response;

/**
 * @author RameshPanyam purpose: Interface class for
 */
public interface ILable {

	public Response craeteLabel(String lableName, String email, String token);

	public List<NoteModel> getAllNotes(String lableId, String token);

	public String updateNote(String token, String lableName);

	public List<NoteModel> sortnoteByTitle();

	public Response addingNote(String lableid, String noteid, String token);

	public Response deleteNoteFromList(String lableid, String noteid, String token);

	public List<NoteModel> sortbyUpdatedDate();
}
