package com.app.residencial.front.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
import com.app.residencial.front.entities.Rol;
import com.app.residencial.front.entities.RolUser;
import com.app.residencial.front.entities.ListRolXUser;

@Named("associaterole")
public class AssociateRoles {

	private Integer rolid;
	private List<ListRolXUser> listRolXUser;
	private String document;


	public List<ListRolXUser> getListRolXUser(){
		return listRolXUser;
	}
	
	public Integer getRolid() {
		return rolid;
	}

	public void setRolid(Integer rolid) {
		this.rolid = rolid;
	}


	public void ejecutar() {
		System.out.println("Esto es una prueba");
	}

	@Bean
	public RestTemplate restTemplateRol() {
		return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;

	public String register(String document) {	
		
		this.document = document;
		
		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		RolUser roluser = new RolUser(this.rolid, document);		

		// build the request
		HttpEntity<RolUser> request = new HttpEntity<>(roluser, headers);

		// send POST request
		ResponseEntity<ResponseRest> response = restTemplate.postForEntity(UrisConfig.getEndpoint_associateRoleUser(), request,
				ResponseRest.class);

		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Post Created");
			System.out.println(response.getBody().getMensaje());
			System.out.println(response.getBody().getCodigo());
			addMessage(response.getBody().getMensaje());
			
			this.loadRolesUser();
			
			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
			addMessage("Ocurrió un error creando el usuario: " + response.getStatusCode());
		}

		return ("Ok...");

	}
	
	public Map<String, Integer> getRoles() {
		return roles;
	}
	
	public Rol[] obtenerListaRoles() {

		Rol[] result = restTemplate.getForObject(UrisConfig.getEndpoint_getListRoles(), Rol[].class);

		return result;

	}
	
	public ListRolXUser[] getRolesUser() {

		String uri = UrisConfig.getEndpoint_getListRolesUser() + this.document;
		
		ListRolXUser[] result = restTemplate.getForObject(uri, ListRolXUser[].class);

		return result;

	}
	
	public void loadRolesUser() {
		ListRolXUser[] listRoles = this.getRolesUser();
		this.listRolXUser = new ArrayList<ListRolXUser>();
		
		for (ListRolXUser masterListRolesXUser : listRoles) {
		    //System.out.println(masterListRolesXUser.getRole());			    
			//this.listRolXUser.add(new ListRolXUser(masterListRolesXUser.getRole(),masterListRolesXUser.getId()));
			//this.listRolXUser.add(new ListRolXUser("prueba","prueba"));		    
			this.listRolXUser.add(masterListRolesXUser);
		}
		
		
	}
	
	
	
	private Map<String, Integer> roles = new HashMap<String, Integer>();	
	
	@PostConstruct
	public void init() {
		
		// Lista de Roles		
		Rol[] listaRoles = this.obtenerListaRoles();
		roles = new HashMap<String, Integer>();
		
		for (Rol masterRol : listaRoles) {
		    //System.out.println(masterRol.getRole());
			roles.put(masterRol.getRole(), masterRol.getId());
		}
		

							
	}
	
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	

}
