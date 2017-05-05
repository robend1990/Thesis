package com.pgs.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pgs.dto.ResponseDTO;
import com.pgs.model.Specialization;
import com.pgs.service.SpecializationService;


@RestController
@RequestMapping(value = "/api/spec")
public class SpecializationController {

	@Autowired
	SpecializationService specializationService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Specialization> getAllSpecializations(){
		return specializationService.getAllSpecializations();
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseDTO saveSpecialization(@RequestBody Specialization specialization){
		return specializationService.saveSpecialization(specialization);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseDTO deleteSpecialization(@RequestBody Specialization specialization){
		return specializationService.deleteSpecialization(specialization);
	}
}
