package com.techgeeknext.security.google.oauth2.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;




@Entity
@Table(name = "users")
public class Users {

	@Id
	private  String username;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	private boolean enabled=true;
	
	@ElementCollection

	@Column(name="AUTHORITY")
	@CollectionTable(name = "AUTHORITIES",joinColumns = {@JoinColumn(name="USERNAME")})
	private Set<String> authorities;
	
	
	
	public Users() {
		authorities= new HashSet<>();
		authorities.add("USER");
	}
	
	
	public Users(String username, String firstName, String lastName,
				 String email, String password, boolean enabled) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.enabled=enabled;
		authorities= new HashSet<>();
		authorities.add("USER");
	}





	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public Set<String> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}
	
	
	
	
	
}
