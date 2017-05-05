package com.pgs.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "user", schema="public")
public class User {

	@Id
	@Column(name = "id")
	@SequenceGenerator(allocationSize = 1 ,name = "user_seq" , sequenceName = "user_seq")
	@GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "user_seq")
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;

	@Column(name = "active")
	private boolean active;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "album_number")
	private Integer albumNumber;
	
	@Column(name = "pesel")
	private Long pesel;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "specialization_id")
	private Specialization specialization;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student")
	private List<Thesis> studentReservations;
	
	@JsonIgnore
	@OneToMany(mappedBy = "advisor")
	private List<Thesis> advisorReservations;
	

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

	public List<Thesis> getStudentReservations() {
		return studentReservations;
	}

	public void setStudentReservations(List<Thesis> studentReservations) {
		this.studentReservations = studentReservations;
	}

	public List<Thesis> getAdvisorReservations() {
		return advisorReservations;
	}

	public void setAdvisorReservations(List<Thesis> advisorReservations) {
		this.advisorReservations = advisorReservations;
	}
	 
	
	
	
}
