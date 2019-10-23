package com.bridgelabz.fundoo.note.service;

import java.util.List;

import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.NoteModel;

public interface Inote {
	public String createNote(NoteDto noteDto);
	public String updateNote(String id,String title,String description);
	public String deleteNote(String id);
	public List< NoteModel> getAllNotes();
	public String pin(String id);
	public String unPin(String id);
	public String archive(String id);
	public String unArchive(String id);
	public String trash(String id);
	public String reStore(String id);

}
