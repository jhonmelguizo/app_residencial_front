package com.app.residencial.front.controller;

import javax.inject.Named;

@Named("asociarroles")
public class AsociacionRoles {
	
	private String prueba;

	public String getPrueba() {
		return prueba;
	}

	public void setPrueba(String prueba) {
		this.prueba = prueba;
	}
	
	public void ejecutar() {
		System.out.println("Esto es una prueba");
	}
	

}
