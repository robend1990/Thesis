package com.pgs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pgs.dto.ResponseDTO;
import com.pgs.dto.UserDTO;
import com.pgs.model.Role;
import com.pgs.model.SecurityUser;
import com.pgs.model.User;
import com.pgs.service.SecurityUserService;
import com.pgs.service.UserService;

@RestController
@RequestMapping(value ="/api")
public class UserController {

	@Autowired
	SecurityUserService securityUserService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/user/me")
	public SecurityUser getLoggedUser(){
		return securityUserService.getLoggedUser();
	}
	
	@RequestMapping(value="/user/checkPassword/{password}", method = RequestMethod.GET)
	public ResponseDTO checkPassword(@PathVariable("password") String password){
		return securityUserService.checkPassword(password);
	}
	
	@RequestMapping(value="/user/changePassword", method = RequestMethod.POST)
	public ResponseDTO checkChange(@RequestBody UserDTO userDTO){
		return userService.changePassword(userDTO);
	}
	
	@RequestMapping(value = "/user/all", method = RequestMethod.GET)
	public List<UserDTO> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/user/roles", method = RequestMethod.GET)
	public List<Role> getAllRoles(){
		return userService.getAllRoles();
	}
	
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public ResponseDTO saveUser(@RequestBody UserDTO userDTO){
		return userService.saveUser(userDTO);
	}
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public ResponseDTO deleteUser(@RequestBody UserDTO userDTO){
		 return userService.deleteUser(userDTO);
	}
	
	@RequestMapping(value = "/user/checkIfUserHasReservation/{albumNumber}", method = RequestMethod.GET)
	public ResponseDTO checkIfUserHasReservation(@PathVariable("albumNumber") Integer albumNumber){
		 return userService.checkIfUserHasReservation(albumNumber);
	}
}
