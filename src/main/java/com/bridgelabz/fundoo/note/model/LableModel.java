package com.bridgelabz.fundoo.note.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author RameshPanyam purpose: Creating Model Class For Label
 */
@Data
@Document("Lable")

public class LableModel {
	@Id
	private String id;
	private String email;
	private String lableName;
	@DBRef(lazy = true)
	private List<NoteModel> noteList = new ArrayList<>();
	public LableModel(String id, String email, String lableName, List<NoteModel> noteList) {
		super();
		this.id = id;
		this.email = email;
		this.lableName = lableName;
		this.noteList = noteList;
	}
	


public LableModel()
{
	}
}

