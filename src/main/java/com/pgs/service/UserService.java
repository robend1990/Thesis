package com.pgs.service;

import io.undertow.attribute.RequestMethodAttribute;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.pgs.dto.ResponseDTO;
import com.pgs.dto.UserDTO;
import com.pgs.model.Role;
import com.pgs.model.User;
import com.pgs.repo.RoleRepository;
import com.pgs.repo.UserRepository;
import com.pgs.utils.ObjectsConverter;
import com.pgs.utils.PasswordHasher;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.queryAll();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for (User user : users) {
			usersDTO.add(ObjectsConverter.userToDTO(user));
		}
		return usersDTO;
	}

	public ResponseDTO saveUser(UserDTO userDTO) {
		ResponseDTO response = new ResponseDTO();
		User user = null;
		if (userDTO.getId() != null) {
			user = userRepository.findOne(userDTO.getId());
		} else {
			user = new User();
			user.setPassword(PasswordHasher.hash(userDTO.getPassword()));
		}
		user.setAlbumNumber(userDTO.getAlbumNumber());
		user.setPesel(user.getPesel());
		user.setSpecialization(userDTO.getSpecialization());
		user.setEmail(userDTO.getEmail());
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setRole(userDTO.getRole());
		user.setAlbumNumber(userDTO.getAlbumNumber());
		userRepository.save(user);
		response.setSuccess(true);
		response.setMessage("Zapisano dane użytkownika");
		return response;
	}

	public List<Role> getAllRoles() {

		return roleRepository.findAll();
	}

	public ResponseDTO deleteUser(UserDTO userDTO) {
		userRepository.delete(userDTO.getId());
		ResponseDTO response = new ResponseDTO();
		response.setSuccess(true);
		response.setMessage("Użytkownik usunięty");
		return response;
	}

	public ResponseDTO changePassword(UserDTO userDTO) {
		ResponseDTO response = new ResponseDTO();
		try {
			User user = userRepository.findOne(userDTO.getId());
			user.setPassword(PasswordHasher.hash(userDTO.getPassword()));
			userRepository.save(user);
			response.setSuccess(true);
			response.setMessage("Hasło zostało zmienione !");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Błąd podczas zmieniania hasła !");
		} finally {
			return response;
		}
	}

	@Transactional
	public ResponseDTO checkIfUserHasReservation(Integer albumNumber) {
		ResponseDTO response = new ResponseDTO();
		try {
			User user = userRepository.findByAlbumNumber(albumNumber);
			if (user == null) {
				response.setSuccess(false);
				response.setMessage("Nie znaleziono studenta o podanym numerze albumu");
			} else if (user.getStudentReservations().size() > 0) {
				response.setSuccess(false);
				response.setMessage("Student posiada już rezerwację !");
			} else if (user.getStudentReservations().size() == 0) {
				response.setSuccess(true);
				response.setMessage("Wyszukano studenta: "
						+ user.getFirstname() + " " + user.getLastname());
			}
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Błąd podczas sprawdzania usera pod kątem rezerwacji");
		} finally {
			return response;
		}
	}
}
