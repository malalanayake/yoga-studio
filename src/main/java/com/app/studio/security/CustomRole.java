package com.app.studio.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Custom Role class for manage the authorities
 * 
 * @author malalanayake
 * 
 */
public class CustomRole implements GrantedAuthority {
	String role = null;

	@Override
	public String getAuthority() {
		return role;
	}

	public void setAuthority(String roleName) {
		this.role = roleName;
	}

}
