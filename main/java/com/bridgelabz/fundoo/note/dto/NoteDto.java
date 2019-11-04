package com.bridgelabz.fundoo.note.dto;

import lombok.Data;

@Data
public class NoteDto {
	private String title;
	private String description;
	private String email;
	public NoteDto(String title, String description, String email) {
		super();
		this.title = title;
		this.description = description;
		this.email = email;
	}
	

}
