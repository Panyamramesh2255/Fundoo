package com.bridgelabz.fundoo.note.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.note.repository.NoteRepository;

@Service

public class NoteService implements Inote {
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	ModelMapper modelMapper;
//	@Autowired
//	NoteDto noteDto;
//	@Autowired
//	NoteModel noteModel;

	@Override
	public String createNote(NoteDto noteDto) {
		System.out.println("we got note dto to service " + noteDto.getTitle() + "" + noteDto.getDescription()+"note email"+noteDto.getEmail());
		NoteModel noteModel = modelMapper.map(noteDto, NoteModel.class);
		noteModel.setCreatedAt(LocalDate.now());
		noteModel.setEditedAt(LocalDate.now());
		noteRepository.save(noteModel);
		// System.out.println(noteModel.);
		return "Note Added sucessfully";
	}

	@Override
	public String updateNote(String id, String title, String description) {
		NoteModel noteModel = noteRepository.findById(id).get();
		if (!title.isEmpty())
			noteModel.setTitle(title);
		if (!description.isEmpty())
			noteModel.setDescription(description);
		noteRepository.save(noteModel);

		return "updated sucessfully...";
	}

	@Override
	public String deleteNote(String id) {
		noteRepository.deleteById(id);
		return "deleted";
	}

	@Override
	public List<NoteModel> getAllNotes() {
		List<NoteModel> list = noteRepository.findAll();
		return list;
	}

	@Override
	public String pin(String id) {
		NoteModel noteModel=noteRepository.findById(id).get();
		noteModel.setPinned(true);
		noteRepository.save(noteModel);
		return "pinned..";
	}

	@Override
	public String unPin(String id) {
		NoteModel noteModel=noteRepository.findById(id).get();
		boolean b=noteModel.isPinned();
		if(b==false)
			return "already unpinned...";
		else
			noteModel.setPinned(false);
		noteRepository.save(noteModel);
		return "unpinned...";
	}

	@Override
	public String archive(String id) {
		NoteModel noteModel=noteRepository.findById(id).get();
		boolean b=noteModel.isArchived();
		if(b==true)
		return "note is already Archived...";
		else
			noteModel.setArchived(true);
		noteRepository.save(noteModel);
		return "Archieved..";
	}

	@Override
	public String unArchive(String id) {
		NoteModel noteModel=noteRepository.findById(id).get();
		boolean b=noteModel.isArchived();
		if(b==false)
		return "note is already UnArchived...";
		else
			noteModel.setArchived(false);
		noteRepository.save(noteModel);
		return "UnArchieved..";
		
	}

	@Override
	public String trash(String id) {
		NoteModel noteModel=noteRepository.findById(id).get();
		boolean response=noteModel.isTrashed();
		if(response==true)
			return "Note is already in Trash..";
	
			noteModel.setTrashed(true);
		noteRepository.save(noteModel);
		return " NOte Trashed...";
	}

	@Override
	public String reStore(String id) {
		
		NoteModel noteModel=noteRepository.findById(id).get();
		boolean response=noteModel.isTrashed();
		if(response==true)
		{
			noteModel.setTrashed(false);
		noteRepository.save(noteModel);
		return "Restored...";
		}
		return "already Restored...";
	}

}
