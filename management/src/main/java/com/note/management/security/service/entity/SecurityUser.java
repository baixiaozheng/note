package com.note.management.security.service.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.note.model.user.User;
import com.note.model.user.UserRole;

public class SecurityUser implements UserDetails {

	private static final long serialVersionUID = -4294694251587167808L;

	private User user;
	
	private boolean isEmail;

	public SecurityUser(User user, boolean isEmail) {
		this.user = user;
		this.isEmail = isEmail;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new LinkedList<GrantedAuthority>();
		// staff role
		Set<UserRole> roles = user.getRoles();
		if (roles == null) {
			roles = new HashSet<UserRole>();
		}
		for (UserRole sr : roles) {
			list.add(new SimpleGrantedAuthority(sr.name()));
		}
		return list;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return isEmail ? user.getEmail() : user.getMobile();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getEnable();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
