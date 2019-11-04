package com.bridgelabz.fundoo.note.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bridgelabz.fundoo.note.util.ENUM;

import lombok.Data;

/**
 * @author RameshPanyam purpose Creating Model class for note
 */
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
	private boolean isActive;
	private boolean isArchived;
	private LocalDateTime reminder;
	private ENUM repeat;
	private boolean isTrashed;
	private List<String> collaborator = new ArrayList<>();
	@DBRef(lazy = true)
	private List<LableModel> lableList = new ArrayList<LableModel>();
	public NoteModel(String title, String description, String email, LocalDate createdAt, LocalDate editedAt,
			boolean isPinned, boolean isActive, boolean isArchived, LocalDateTime reminder, ENUM repeat,
			boolean isTrashed, List<String> collaborator, List<LableModel> lableList) {
		
		this.title = title;
		this.description = description;
		this.email = email;
		this.createdAt = createdAt;
		this.editedAt = editedAt;
		this.isPinned = isPinned;
		this.isActive = isActive;
		this.isArchived = isArchived;
		this.reminder = reminder;
		this.repeat = repeat;
		this.isTrashed = isTrashed;
		this.collaborator = collaborator;
		this.lableList = lableList;
	}
	
	

}
