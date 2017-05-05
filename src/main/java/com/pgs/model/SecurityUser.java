package com.pgs.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Transactional
public class SecurityUser extends User implements UserDetails{

	public SecurityUser(){
		
	}
	
	public SecurityUser(User user){
		this.setId(user.getId());
		this.setFirstname(user.getFirstname());
		this.setLastname(user.getLastname());
		this.setPassword(user.getPassword());
		this.setUsername(user.getUsername());
		this.setActive(user.isActive());
		this.setRole(user.getRole());
		this.setEmail(user.getEmail());
		this.setPesel(user.getPesel());
		this.setAlbumNumber(user.getAlbumNumber());
	}
	
	@Override
	@Transactional
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.getRole().getName()));
		return authorities;
	}

	@Override
	public String getPassword(){
		return super.getPassword();
	}
	
	@Override
	public String getUsername(){
		return super.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
