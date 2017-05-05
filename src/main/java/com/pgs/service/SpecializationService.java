package com.pgs.service;

import java.util.List;

import org.neo4j.cypher.internal.compiler.v2_0.untilMatched;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgs.dto.ResponseDTO;
import com.pgs.model.Specialization;
import com.pgs.repo.SpecializationRepository;

@Service
public class SpecializationService {

	@Autowired
	SpecializationRepository specializationRepository;
	
	public List<Specialization> getAllSpecializations(){
		return specializationRepository.findAll();
	}
	
	public ResponseDTO saveSpecialization(Specialization specialization){
		ResponseDTO response = new ResponseDTO();
		specializationRepository.save(specialization);
		response.setMessage("Zapisano kierunek");
		response.setSuccess(true);
		return response;
	}
	
	public ResponseDTO deleteSpecialization(Specialization specialization){
		ResponseDTO response = new ResponseDTO();
		try{
			specializationRepository.delete(specialization.getId());
		} catch(Exception e){
			response.setMessage("Błąd podczas usuwanie kierunku");
			return response;
		}
		response.setSuccess(true);
		response.setMessage("Usunięto Kierunek");
		return response;
	}
}
