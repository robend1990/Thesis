package com.pgs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.pgs.dto.ResponseDTO;
import com.pgs.model.SecurityUser;
import com.pgs.model.User;
import com.pgs.repo.UserRepository;

@Component
public class SecurityUserService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null){
			throw new UsernameNotFoundException("User with email " + email + " not found");
		}
		return new SecurityUser(user);
	}
	
	public SecurityUser getLoggedUser(){
		SecurityUser loggedUser = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return loggedUser;
	}
	
	public ResponseDTO checkPassword(String password){
		ResponseDTO response = new ResponseDTO();
		SecurityUser user = getLoggedUser();
		if(BCrypt.checkpw(password, user.getPassword())){
			response.setSuccess(true);
		}
		return response;
	}
	

}
