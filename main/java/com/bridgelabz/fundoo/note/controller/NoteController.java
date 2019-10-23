package com.bridgelabz.fundoo.note.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.note.service.Inote;
import com.bridgelabz.fundoo.note.service.LableService;
import com.bridgelabz.fundoo.note.service.NoteService;
import com.bridgelabz.fundoo.response.Response;

@RestController
@RequestMapping("/note")
public class NoteController {
	@Autowired
	Inote noteService;
	@Autowired
	LableService lableService;

	@PostMapping("/create-note")
	public ResponseEntity<String> createNode(@RequestBody NoteDto noteDto) {
		return new ResponseEntity<String>(noteService.createNote(noteDto), HttpStatus.OK);
	}

	@GetMapping("/getall-notes")
	public ResponseEntity<List<NoteModel>> getAllNotes() {
		return new ResponseEntity<List<NoteModel>>(noteService.getAllNotes(), HttpStatus.OK);
	}

	@DeleteMapping("/delete-note")
	public ResponseEntity<String> deleteNode(@RequestParam String id) {
		return new ResponseEntity<String>(noteService.deleteNote(id), HttpStatus.OK);
	}

	@PutMapping("/update-note")
	public ResponseEntity<String> update(@RequestParam String id, @RequestParam String title,
			@RequestParam String description) {

		return new ResponseEntity<String>(noteService.updateNote(id, title, description), HttpStatus.OK);
	}

	@GetMapping("/pin-note")
	public ResponseEntity<String> pin(@RequestParam String id) {

		return new ResponseEntity<String>(noteService.pin(id), HttpStatus.OK);
	}

	@GetMapping("/unpin-note")
	public ResponseEntity<String> unPin(@RequestParam String id) {
		return new ResponseEntity<String>(noteService.unPin(id), HttpStatus.OK);
	}

	@GetMapping("/archive-note")
	public ResponseEntity<String> archive(@RequestParam String id) {
		return new ResponseEntity<String>(noteService.archive(id), HttpStatus.OK);
	}

	@GetMapping("/unarchive-note")
	public ResponseEntity<String> unArchive(@RequestParam String id) {
		return new ResponseEntity<String>(noteService.unArchive(id), HttpStatus.OK);
	}
	@GetMapping("trash-note")
	public ResponseEntity<String>trash(@RequestParam String id)
	{
		return new ResponseEntity<String>(noteService.trash(id),HttpStatus.OK);
	}
	@GetMapping("/restore-note")
	public ResponseEntity<String> restore(@RequestParam String id)
	{
		return new ResponseEntity<String>(noteService.reStore(id),HttpStatus.OK);
	}
	@PostMapping("/create-lable")
	public Response createLable(@RequestParam String lablename,@RequestParam String email)
	{
		return lableService.craeteLabel(lablename, email);
	}
	@PostMapping("/add-Note-to-lable")
	public void addNote(@RequestParam String noteid,@RequestParam String lableid)
	{
		lableService.addingNote(noteid,lableid);
	}
	@PutMapping("/delete-Note-from-lable")
	public void deleteNoteFromList(@RequestParam String noteid,@RequestParam String lableid)
	{
		lableService.deleteNote(noteid,lableid);
	}
	@GetMapping("/sort-note-by-title")
	public List<NoteModel> sortNote()
	{
	return lableService.sortnoteByTitle();
	}
	@GetMapping("/sort-note-by-updated-date")
	public List<NoteModel>sortByUpdatedDate()
	{
		return lableService.sortbyUpdatedDate();
	}

}
