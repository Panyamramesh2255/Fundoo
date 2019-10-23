package com.bridgelabz.fundoo.note.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document("Lable")
public class LableModel {
	@Id
	private String id; 
	private String email;
	private String lableName;
	@DBRef
	private List<NoteModel> noteList= new ArrayList<>();
	
}
