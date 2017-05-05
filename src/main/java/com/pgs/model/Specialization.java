package com.pgs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="specialization", schema="public" )
public class Specialization {

	@Id
	@Column(name = "id")
	@SequenceGenerator(allocationSize = 1 ,name = "specialization_seq" , sequenceName = "specialization_seq")
	@GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "specialization_seq")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="degree")
	private String degree;
	
	@Column(name="process")
	private String process;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	
	
}
