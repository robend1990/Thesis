package com.pgs.dto;

import com.pgs.model.Thesis;

public class ReservationDTO {

	Thesis thesis;
	Integer albumNumber;
	public Thesis getThesis() {
		return thesis;
	}
	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
	}
	public Integer getAlbumNumber() {
		return albumNumber;
	}
	public void setAlbumNumber(Integer albumNumber) {
		this.albumNumber = albumNumber;
	}
	
	
}
