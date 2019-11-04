package com.bridgelabz.fundoo.note.service;

import java.time.LocalDateTime;
import java.util.List;

import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.note.util.ENUM;
import com.bridgelabz.fundoo.response.Response;

/**
 * 
 * @author PanyamRamesh purpose:Interface for note
 */
public interface Inote {

	public Response createNote(NoteDto noteDto, String verifiedEmail);

	public Response updateNote(String id, String title, String description, String verifiedEmail);

	public Response deleteNote(String id, String verifiedEmail);

	public List<NoteModel> getAllNotes();

	public Response pin(String id, String verfiedEmail);

	public Response archive(String id, String token);

	public Response unArchive(String id, String token);

	public Response trash(String id, String token);

	public Response reStore(String id, String token);

	public Response addTOCollaborator(String id, String email);

	public Response addReminder(LocalDateTime reminder, ENUM repeat, String id, String token);

	public Response updateReminder(LocalDateTime reminder, ENUM repeat, String id, String token);

	public Response deleteReminder(String id, String token);

}
