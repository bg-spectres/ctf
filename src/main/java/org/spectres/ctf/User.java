package org.spectres.ctf;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User {
	@Id
	private Integer user_id;
	
	private String first_name;
	private String last_name;
	private String user;
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	private Date last_login;
	
	private Integer failed_login;
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLast_login() {
		return last_login;
	}
	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}
	public Integer getFailed_login() {
		return failed_login;
	}
	public void setFailed_login(Integer failed_login) {
		this.failed_login = failed_login;
	}
	
	public Integer getId() {
		return user_id;
	}
	
	public String getDisplayName() {
		return first_name + " " + last_name;
	}
	
	@Override
	public String toString() {
		return "User		id: " + getUser_id() +
				"		name: " + getDisplayName() 
				;
	}
}
