package com.example.demoTodo;

import com.example.demoTodo.Model.Us;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

	private Us us;

	public CustomUserDetails(Us us) {
		this.us = us;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return us.getPassword();
	}

	@Override
	public String getUsername() {
		return us.getEmail();
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
		return true;
	}

//    public String getUsername(String o) {
//		return us.getEmail();
//    }

}
