package com.pgs.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgs.dto.ReservationDTO;
import com.pgs.dto.ResponseDTO;
import com.pgs.dto.ThesisDTO;
import com.pgs.model.Thesis;
import com.pgs.model.User;
import com.pgs.repo.ThesisRepository;
import com.pgs.repo.UserRepository;

@Service
public class ThesisService {

	@Autowired
	ThesisRepository thesisRepository;
	
	@Autowired
	SecurityUserService securityUserService;
	
	@Autowired
	UserRepository userRepository;
	
	public List<Thesis> getAll(){
		return thesisRepository.findAll();
	}

	public ResponseDTO save(Thesis thesis) {
		ResponseDTO response = new ResponseDTO();
		try{
			User loggedAdvisor = userRepository.findOne(securityUserService.getLoggedUser().getId());
			thesis.setAdvisor(loggedAdvisor);
			thesisRepository.save(thesis);
			response.setSuccess(true);
			response.setMessage("Zapisano Temat");
		} catch(Exception e){
			response.setSuccess(false);
			response.setMessage("Błąd podczas zapisywania tematu");
		} finally{
			return response;
		}
	}
	
	public ResponseDTO delete(Thesis thesis) {
		ResponseDTO response = new ResponseDTO();
		try{
			thesisRepository.delete(thesis);
			response.setSuccess(true);
			response.setMessage("Usunięto Temat");
		} catch(Exception e){
			response.setSuccess(false);
			response.setMessage("Błąd podczas usuwania tematu");
		} finally{
			return response;
		}
	}
	
	public ResponseDTO makeReservation(ReservationDTO reservationDTO) {
		ResponseDTO response = new ResponseDTO();
		try{
			User user = userRepository.findByAlbumNumber(reservationDTO.getAlbumNumber());
			if(user == null){
				response.setSuccess(false);
				response.setMessage("Nie znaleziono studenta o podanym numerze albumu");
			} else{
				Thesis thesis = thesisRepository.findOne(reservationDTO.getThesis().getId());
				thesis.setStudent(user);
				thesisRepository.save(thesis);
				response.setSuccess(true);
				response.setMessage("Zarezerwowano temat!");
			}	
		} catch(Exception e){
			response.setSuccess(false);
			response.setMessage("Błąd podczas rezerwacji tematu");
		} finally{
			return response;
		}
	}
	
	public ResponseDTO cancelReservation(Long thesisId) {
		ResponseDTO response = new ResponseDTO();
		try{
			Thesis thesis = thesisRepository.findOne(thesisId);
			if(thesis == null){
				response.setSuccess(false);
				response.setMessage("Nie znaleziono tematu");
			}else{
				thesis.setStudent(null);
				thesisRepository.save(thesis);
				response.setSuccess(true);
				response.setMessage("Anulowano rezerwację");
			}
			
		} catch(Exception e){
			response.setSuccess(false);
			response.setMessage("Błąd podczas anulowania rezerwacji");
		} finally{
			return response;
		}
	}
	
	public ResponseDTO setDefenseDate(ThesisDTO thesisDTO) {
		ResponseDTO response = new ResponseDTO();
		try{
			Thesis thesis = thesisRepository.findOne(thesisDTO.getThesisId());
			if(thesis == null){
				response.setSuccess(false);
				response.setMessage("Nie znaleziono tematu");
			} else{
				Timestamp defenseDate = new Timestamp(thesisDTO.getMiliseconds());
				thesis.setDefenseDate(defenseDate);
				thesisRepository.save(thesis);
			}
			response.setSuccess(true);
			response.setMessage("Ustalono termin obrony");
		} catch(Exception e){
			response.setSuccess(false);
			response.setMessage("Błąd podczas ustalania terminu obrony tematu");
		} finally{
			return response;
		}
	}
	
	public Thesis getMyThesis(Long studentId) {
			
			Thesis thesis = thesisRepository.findByStudentId(studentId);
			return thesis;
	}
}
