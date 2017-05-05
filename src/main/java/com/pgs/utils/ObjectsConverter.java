package com.pgs.utils;

import com.pgs.dto.UserDTO;
import com.pgs.model.User;

public class ObjectsConverter {

	static public UserDTO userToDTO(User user){
		UserDTO dto = new UserDTO();
		dto.setActive(user.isActive());
		dto.setEmail(user.getEmail());
		dto.setFirstname(user.getFirstname());
		dto.setId(user.getId());
		dto.setLastname(user.getLastname());
		dto.setPassword(user.getPassword());
		dto.setRole(user.getRole());
		dto.setUsername(user.getUsername());
		dto.setPesel(user.getPesel());
		dto.setAlbumNumber(user.getAlbumNumber());
		dto.setSpecialization(user.getSpecialization());
		return dto;
	}
	
}
