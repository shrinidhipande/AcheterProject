package com.acheter.accountservice.security.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = -518681763308498641L;
	private String username;
	private String password;
	private String roleName;
	private String status;
	private boolean nonLocked;
	private boolean enabled;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(String username, String password, String roleName, String status) {
		this.username = username;
		this.password = password;
		this.roleName = roleName;
		this.status = status;

		this.authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(this.roleName));

		nonLocked = (this.status != null && this.status.equals("L")) ? false : true;
		enabled = (this.status != null && this.status.equals("N") || this.status.equals("D")) ? false : true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return nonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
