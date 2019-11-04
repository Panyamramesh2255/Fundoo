package com.bridgelabz.fundoo.note.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.bridgelabz.fundoo.note.util.ENUM;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.util.Util;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private Inote noteService;
	@Autowired
	Util util;

	/**
	 * purpose: Creating note
	 * 
	 * @param noteDto
	 * @return
	 */
	@PostMapping("/note")
	public ResponseEntity<Response> createNode(@RequestBody NoteDto noteDto, @RequestHeader String token) {

		Response response = noteService.createNote(noteDto, util.decode(token));
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

		Response response = noteService.deleteNote(id, util.decode(token));
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

		Response response = noteService.updateNote(id, title, description, util.decode(token));
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

		Response response = noteService.pin(id, util.decode(token));
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

		Response response = noteService.archive(id, util.decode(token));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: Making note not archived
	 * 
	 * @param id
	 * @return
	 */
	@PutMapping("/unarchivenote")
	public ResponseEntity<Response> unArchive(@RequestParam String id, @RequestHeader String token) {

		Response response = noteService.unArchive(id, util.decode(token));
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

	/**
	 * purpose: adding reminder
	 * 
	 * @param reminder
	 * @param id
	 * @param token
	 * @return
	 */
	@PostMapping("/reminder")
	public ResponseEntity<Response> addReminder(
			@RequestParam("reminder") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reminder,
			@RequestParam("repeat") ENUM repeat, @RequestHeader String id, @RequestHeader String token) {
		Response response = noteService.addReminder(reminder, repeat, id, util.decode(token));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose:updating reminder
	 * 
	 * @param reminder
	 * @param id
	 * @param token
	 * @return
	 */
	@PutMapping("/reminder")
	public ResponseEntity<Response> updateReminder(
			@RequestParam("reminder") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reminder,
			@RequestParam("repeat") ENUM repeat, @RequestHeader String id, @RequestHeader String token) {
		Response response = noteService.updateReminder(reminder, repeat, id, util.decode(token));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * purpose: deleting reminder
	 * 
	 * @param id
	 * @param token
	 * @return
	 */
	@DeleteMapping("/reminder")
	public ResponseEntity<Response> deleteReminder(@RequestHeader String id, @RequestHeader String token) {
		Response response = noteService.deleteReminder(id, util.decode(token));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
