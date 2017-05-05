package com.pgs.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="thesis", schema="public")
public class Thesis {

	@Id
	@Column(name = "id")
	@SequenceGenerator(allocationSize = 1 ,name = "thesis_seq" , sequenceName = "thesis_seq")
	@GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "thesis_seq")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "reserved")
	private boolean reserved;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm" ,timezone="CET")
	@Column(name = "defense_date")
	private Timestamp defenseDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "specialization_id")
	private Specialization specialization;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "student_id")
	private User student;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "advisor_id")
	private User advisor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public Timestamp getDefenseDate() {
		return defenseDate;
	}

	public void setDefenseDate(Timestamp defenseDate) {
		this.defenseDate = defenseDate;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public User getAdvisor() {
		return advisor;
	}

	public void setAdvisor(User advisor) {
		this.advisor = advisor;
	}
	
	
	
}
