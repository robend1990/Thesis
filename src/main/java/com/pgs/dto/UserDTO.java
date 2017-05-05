package com.pgs.dto;

import com.pgs.model.Role;
import com.pgs.model.Specialization;

public class UserDTO {

	private Long id;
	
	
	private String username;
	
	
	private String password;
	
	
	private String firstname;
	
	
	private String lastname;

	
	private boolean active;
	
	
	private String email;
	
	private Integer albumNumber;
	
	private Long pesel;
	
	private Role role;
	
	private Specialization specialization;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole(){
		return role;
	}
	
	public void setRole(Role role){
		this.role = role;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public Integer getAlbumNumber() {
		return albumNumber;
	}

	public void setAlbumNumber(Integer albumNumber) {
		this.albumNumber = albumNumber;
	}

	public Long getPesel() {
		return pesel;
	}

	public void setPesel(Long pesel) {
		this.pesel = pesel;
	}
	
	
}
