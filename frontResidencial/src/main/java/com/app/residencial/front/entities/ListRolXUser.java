package com.app.residencial.front.entities;

public class ListRolXUser {
	
	private String role;
	private String id;
	
	//Constructor
	public ListRolXUser() {
		
	}
	
	public ListRolXUser(String role, String id) {
		this.role = role;
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
