package com.app.residencial.front.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
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
import com.app.residencial.front.entities.ResponseRest;
import com.app.residencial.front.entities.State;
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
	private Integer state;

	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	

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

	public Map<String, Integer> getStates() {
		return states;
	}



	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	public String registrar() {

		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		User user = new User(this.TipoDocumento, this.NumeroDocumento, this.Nombres, this.Apellidos, this.Email,
				this.DirEnvio, Long.parseLong(this.Telefono), Long.parseLong(this.Celular), this.state);

		// build the request
		HttpEntity<User> request = new HttpEntity<>(user, headers);

		// send POST request
		ResponseEntity<ResponseRest> response = restTemplate.postForEntity(UrisConfig.getEndpoint_createUser(), request,
				ResponseRest.class);

		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Post Created");
			System.out.println(response.getBody().getMensaje());
			System.out.println(response.getBody().getCodigo());
			addMessage(response.getBody().getMensaje());
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
			addMessage("Ocurrió un error creando el usuario: " + response.getStatusCode());
		}

		return ("Ok...");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}


	
	public State[] getListUsersStates() {

		State[] result = restTemplate.getForObject(UrisConfig.getEndpoint_getListStatesUser(), State[].class);

		return result;

	}

	private Map<String, Integer> states = new HashMap<String, Integer>();

	@PostConstruct
	public void init() {
		
		
		//List States User
		State[] listStates = this.getListUsersStates();
		states = new HashMap<String, Integer>();
		
		for (State masterStates : listStates) {
		    //System.out.println(masterRol.getRole());
			states.put(masterStates.getName(), masterStates.getId());
		}
		
						

	}

}
