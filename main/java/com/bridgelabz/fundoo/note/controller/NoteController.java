package com.bridgelabz.fundoo.note.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	Environment environment;
	@Autowired
	Inote noteService;
	@Autowired
	LableService lableService;

	/**
	 * purpose: Creating note
	 * 
	 * @param noteDto
	 * @return
	 */
	@PostMapping("/note")
	public ResponseEntity<Response> createNode(@RequestBody NoteDto noteDto, @RequestHeader String token) {
		Response response = noteService.createNote(noteDto, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: Getting all the notes
	 * 
	 * @return
	 */
	@GetMapping("/notes")
	public ResponseEntity<List<NoteModel>> getAllNotes() {
		return new ResponseEntity<List<NoteModel>>(noteService.getAllNotes(), HttpStatus.OK);
	}

	/**
	 * purpose: Deleting note
	 * 
	 * @param id
	 * @return Deleting a note
	 */
	@DeleteMapping("/note")
	public ResponseEntity<Response> deleteNote(@RequestParam String id, @RequestHeader String token) {
		Response response = noteService.deleteNote(id, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	/**
	 * purpose: Updating note
	 * 
	 * @param id
	 * @param title
	 * @param description
	 * @return Updating the note
	 */
	@PutMapping("/note")
	public ResponseEntity<Response> update(@RequestParam String id, @RequestParam String title,
			@RequestParam String description, @RequestHeader String token) {

		Response response = noteService.updateNote(id, title, description, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: Making note pin
	 * 
	 * @param id
	 * @return Making note pin
	 */

	@PutMapping("/pinnote")
	public ResponseEntity<Response> pin(@RequestParam String id, @RequestHeader String token) {

		Response response = noteService.pin(id, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: Making note unpin
	 * 
	 * @param id
	 * @return Making un-pin note
	 */
	@PutMapping("/unpinnote")
	public ResponseEntity<Response> unPin(@RequestParam String id, @RequestHeader String token) {
		Response response = noteService.unPin(id, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: Making note archive
	 * 
	 * @param id
	 * @return Making note archive
	 */
	@PutMapping("/archivenote")
	public ResponseEntity<Response> archive(@RequestParam String id, @RequestHeader String token) {
		Response response = noteService.archive(id, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: Making note unarchive
	 * 
	 * @param id
	 * @return
	 */
	@PutMapping("/unarchivenote")
	public ResponseEntity<Response> unArchive(@RequestParam String id, @RequestHeader String token) {
		Response response = noteService.unArchive(id, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: Adding note to trash
	 * 
	 * @param id
	 * @return
	 */
	@PutMapping("trashnote")
	public ResponseEntity<Response> trash(@RequestParam String id, @RequestHeader String token) {
		Response response = noteService.trash(id, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: Restoring note from trash
	 * 
	 * @param id
	 * @return
	 */
	@PutMapping("/restorenote")
	public ResponseEntity<Response> restore(@RequestParam String id, @RequestHeader String token) {
		Response response = noteService.reStore(id, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: Creating a label
	 * 
	 * @param lablename
	 * @param email
	 * @return
	 */
	@PostMapping("/lable")
	public ResponseEntity<Response> createLable(@RequestParam String lablename, @RequestParam String email,
			@RequestHeader String token) {
		Response response = lableService.craeteLabel(lablename, email, token);

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	/**
	 * purpose: Adding a note to label
	 *
	 * @param noteid
	 * @param lableid
	 * @return
	 */
	@PostMapping("/addnotetolable")
	public ResponseEntity<Response> addNote(@RequestParam String noteid, @RequestParam String lableid,
			@RequestHeader String token) {
		Response response = lableService.addingNote(noteid, lableid, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	/**
	 * purpose: Deleting note from list
	 * 
	 * @param noteid
	 * @param lableid
	 * @return
	 */
	@DeleteMapping("/notefromlable")
	public ResponseEntity<Response> deleteNotefromList(@RequestParam String noteid, @RequestParam String lableid,
			@RequestHeader String token) {
		Response response = lableService.deleteNoteFromList(noteid, lableid, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	/**
	 * purpose: Sorting notes by title
	 * 
	 * @return
	 */
	@GetMapping("/sortnotebytitle")
	public List<NoteModel> sortNote() {
		return lableService.sortnoteByTitle();
	}

	/**
	 * purpose: Sorting notes by updated date
	 * 
	 * @return
	 */
	@GetMapping("/sortnotebyupdateddate")
	public List<NoteModel> sortByUpdatedDate() {
		return lableService.sortbyUpdatedDate();
	}

	/**
	 * purpose: Getting all notes of label
	 * 
	 * @param lableid
	 * @return
	 */
	@GetMapping("/getAllNotesOfLable")
	public List<NoteModel> getAllNotes(@RequestParam String lableid, @RequestHeader String token) {
		return lableService.getAllNotes(lableid, token);
	}

	/**
	 * purpose: Making Collaborator list
	 * 
	 * @param id
	 * @param email
	 * @return
	 */
	@PutMapping("/collaborator")
	public ResponseEntity<Response> addCollaborator(@RequestHeader String id, @RequestHeader String email) {
		Response response = noteService.addTOCollaborator(id, email);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
