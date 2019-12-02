package com.bridgelabz.fundoo.note.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.note.model.NoteModel;
import com.bridgelabz.fundoo.note.service.LableService;
import com.bridgelabz.fundoo.response.Response;
/**
 * purpose: Controller for Label
 * @author PanyamRamesh
 *
 */
@RestController
@RequestMapping("/label")
public class LabelController {
	@Autowired
	private LableService lableService;

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

}
