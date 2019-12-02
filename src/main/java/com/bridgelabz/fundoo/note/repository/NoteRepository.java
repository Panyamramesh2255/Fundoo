package com.bridgelabz.fundoo.note.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.note.model.NoteModel;

/**
 * @author RameshPanyam purpose: Creating a repository for notemodel class
 */
@Repository

public interface NoteRepository extends MongoRepository<NoteModel, String> {
	public List<NoteModel> findByEmail(String email);
	public NoteModel findByIdAndEmail(String id,String email);

}
