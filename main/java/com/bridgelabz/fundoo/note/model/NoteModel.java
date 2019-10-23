package com.bridgelabz.fundoo.note.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Note")

public class NoteModel {
	@Id
	private String id;

	private String title;
	private String description;
	private String email;
	private LocalDate createdAt;
	private LocalDate editedAt;
	private boolean isPinned;
	private boolean isArchived;
	private boolean isTrashed;
	@DBRef
	private List<LableModel> lableList=new ArrayList<LableModel>();

}
