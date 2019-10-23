package com.bridgelabz.fundoo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.dto.RegistrationDto;
import com.bridgelabz.fundoo.model.RegistrationModel;
@Repository
public interface IRegistrationRepository extends MongoRepository<RegistrationModel, String> {
	public void deleteByUserName(String userName);

//public RegistrationModel findByEmai(String email);
	public RegistrationModel findByEmail(String email);

	public RegistrationModel findByUserName(String username);

}
