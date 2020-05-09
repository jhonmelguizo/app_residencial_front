package com.app.residencial.frontResidencial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registraUsuario")
public class RegistrarUsuario {
	
	@RequestMapping(method=RequestMethod.GET)
	public String registrar() {
		return("Hola Mundooo Prueba");
	}
	
	

}
