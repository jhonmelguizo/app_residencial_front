package com.app.residencial.front.entities;

public class User {

	private String typedocument;
	private String document;
	private String names;
	private String lastnames;
	private String email;
	private String address;
	private long phone;
	private long movil;

	//Constructor
	public User() {
	}

	public User(String typedocument, String document, String names, String lastnames, String emails, String address, long phone, long movil) {
		this.typedocument = typedocument;
		this.document = document;
		this.names = names;
		this.lastnames = lastnames;
		this.email = emails;
		this.address = address;
		this.phone = phone;
		this.movil = movil;
	}

	public String getTypedocument() {
		return typedocument;
	}

	public void setTypedocument(String typedocument) {
		this.typedocument = typedocument;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastnames() {
		return lastnames;
	}

	public void setLastnames(String lastnames) {
		this.lastnames = lastnames;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public long getMovil() {
		return movil;
	}

	public void setMovil(long movil) {
		this.movil = movil;
	}

}
