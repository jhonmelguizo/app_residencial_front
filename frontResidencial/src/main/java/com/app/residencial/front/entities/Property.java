package com.app.residencial.front.entities;

public class Property {
	
	private String name;
	private int fk_type;
	private String fk_user;
	
	public Property(String name, int fk_type, String fk_user) {
		super();
		this.name = name;
		this.fk_type = fk_type;
		this.fk_user = fk_user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFk_type() {
		return fk_type;
	}

	public void setFk_type(int fk_type) {
		this.fk_type = fk_type;
	}

	public String getFk_user() {
		return fk_user;
	}

	public void setFk_user(String fk_user) {
		this.fk_user = fk_user;
	}
	
	
	
	
	
	
	
	
	
	
	

}
