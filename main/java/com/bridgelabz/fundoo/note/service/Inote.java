package com.bridgelabz.fundoo.note.service;

import java.util.List;

import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.response.Response;

public interface Inote {
	public Response createNote(NoteDto noteDto, String token);

	public Response updateNote(String id, String title, String description, String token);

	public Response deleteNote(String id, String token);

	public List<NoteModel> getAllNotes();

	public Response pin(String id, String token);

	public Response unPin(String id, String token);

	public Response archive(String id, String token);

	public Response unArchive(String id, String token);

	public Response trash(String id, String token);

	public Response reStore(String id, String token);

	// public Response verifying(String token);
	public Response addTOCollaborator(String id, String email);

}
