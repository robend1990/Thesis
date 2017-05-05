package com.pgs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pgs.dto.ReservationDTO;
import com.pgs.dto.ResponseDTO;
import com.pgs.dto.ThesisDTO;
import com.pgs.model.Thesis;
import com.pgs.service.ThesisService;

@RestController
@RequestMapping(value = "/api/thesis")
public class ThesisController {

	@Autowired
	ThesisService thesisService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Thesis> getAll(){
		return thesisService.getAll();
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseDTO save(@RequestBody Thesis thesis){
		return thesisService.save(thesis);
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public ResponseDTO delete(@RequestBody Thesis thesis){
		return thesisService.delete(thesis);
	}
	
	@RequestMapping(value="/makeReservation", method = RequestMethod.POST)
	public ResponseDTO delete(@RequestBody ReservationDTO reservationDTO){
		return thesisService.makeReservation(reservationDTO);
	}
	
	@RequestMapping(value="/cancelReservation/{thesisId}", method = RequestMethod.POST)
	public ResponseDTO delete(@PathVariable("thesisId") Long thesisId){
		return thesisService.cancelReservation(thesisId);
	}
	
	@RequestMapping(value="/setDefenseDate", method = RequestMethod.POST)
	public ResponseDTO setDefenseDate(@RequestBody ThesisDTO thesis){
		return thesisService.setDefenseDate(thesis);
	}
	
	@RequestMapping(value="/myThesis/{studentId}", method = RequestMethod.GET)
	public Thesis getMyThesis(@PathVariable("studentId") Long studentId){
		return thesisService.getMyThesis(studentId);
	}
}
