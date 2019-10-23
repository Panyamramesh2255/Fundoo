package com.bridgelabz.fundoo.note.service;

import com.bridgelabz.fundoo.response.Response;

public interface ILable {
	public  Response craeteLabel(String lableName,String email);
	public String getAllNotes(String token,String lableId);
	public String updateNote(String token,String lableName);
	public Response deleteNote(String token,String lableId);
	public Response addingNote(String lableid,String noteid);
	public Response deleteNoteList(String lableid,String noteid);

}
