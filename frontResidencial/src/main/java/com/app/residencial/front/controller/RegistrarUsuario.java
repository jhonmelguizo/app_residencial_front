package com.app.residencial.front.controller;

import java.util.Collections;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.residencial.front.config.UrisConfig;
import com.app.residencial.front.entities.User;


@Named("registrar")
public class RegistrarUsuario {
	
	private String TipoDocumento = "";
	private String NumeroDocumento;
	private String Nombres;
	private String Apellidos;
	private String Email;
	private String DirEnvio;
	private String Telefono;
	private String Celular;
	

	public String getTipoDocumento() {
		return TipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return NumeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		NumeroDocumento = numeroDocumento;
	}
	
	public String getNombres() {
		return Nombres;
	}

	public void setNombres(String nombres) {
		Nombres = nombres;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDirEnvio() {
		return DirEnvio;
	}

	public void setDirEnvio(String dirEnvio) {
		DirEnvio = dirEnvio;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getCelular() {
		return Celular;
	}

	public void setCelular(String celular) {
		Celular = celular;
	}	
		

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	

	@Autowired
	private RestTemplate restTemplate;
	
	public String consumirServicio() {

		final String uri = "https://gturnquist-quoters.cfapps.io/api/random";

		String result = restTemplate.getForObject(uri, String.class);		
		
		return result;
		
	}
	
	
	public String registrar() {
		
		
		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		User user = new User(this.TipoDocumento, this.NumeroDocumento, this.Nombres, this.Apellidos,
				this.Email, this.DirEnvio, Long.parseLong(this.Telefono), Long.parseLong(this.Celular));
		
		// build the request
		HttpEntity<User> request = new HttpEntity<>(user, headers);
		
		// send POST request
		ResponseEntity<User> response = restTemplate.postForEntity(UrisConfig.getEndpoint_createUser(), request, User.class);
		
		// check response
		if (response.getStatusCode() == HttpStatus.CREATED) {
		    System.out.println("Post Created");
		    System.out.println(response.getBody());
		} else {
		    System.out.println("Request Failed");
		    System.out.println(response.getStatusCode());
		}
		
		addMessage("Usuario registrado exitosamente!!" + this.Celular);
		return ("Ok...");
	}					
		

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
