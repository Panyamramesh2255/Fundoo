package com.bridgelabz.fundoo.note.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.note.model.LableModel;

/**
 * @author RameshPanyam purpose: Creating a repository for labelmodel class
 */
@Repository

public interface LabelRepository extends MongoRepository<LableModel, String> {
	public List<LableModel> findByEmail(String email);

}
