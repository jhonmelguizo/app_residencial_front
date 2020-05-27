package com.app.residencial.front.entities;

public class RolUser {
	
	private String userid;
	private int rolid;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getRolid() {
		return rolid;
	}
	public void setRolid(int rolid) {
		this.rolid = rolid;
	}
	
	public RolUser() {
		
	}
	
	public RolUser(int rolid, String userid) {
		this.userid = userid;
		this.rolid = rolid;
	}

}
