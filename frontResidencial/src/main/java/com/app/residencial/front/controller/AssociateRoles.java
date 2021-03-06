package com.app.residencial.front.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.app.residencial.front.services.MessageService;

@Named("associaterole")
public class AssociateRoles {

	private Integer rolid;
	private List<ListRolXUser> listRolXUser;
	private String document;

	public List<ListRolXUser> getListRolXUser() {
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
	@Qualifier("restTemplateRoles")
	public RestTemplate restTemplateRol() {
		return new RestTemplate();
	}

	@Autowired
	@Qualifier("restTemplateRoles")
	private RestTemplate restTemplateRoles;

	public String register(String document) {

		if (document.equals("")) {
			MessageService.addMessage("Debe ingresar un numero de documento o consultar usuario");
		} else {

			this.document = document;

			// create headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			RolUser roluser = new RolUser(this.rolid, document);

			// build the request
			HttpEntity<RolUser> request = new HttpEntity<>(roluser, headers);

			// send POST request
			ResponseEntity<ResponseRest> response = restTemplateRoles
					.postForEntity(UrisConfig.getEndpoint_associateRoleUser(), request, ResponseRest.class);

			// check response
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println("Post Created");
				System.out.println(response.getBody().getMensaje());
				System.out.println(response.getBody().getCodigo());
				MessageService.addMessage(response.getBody().getMensaje());

				this.loadRolesUser();

			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
				MessageService.addMessage("Ocurrió un error creando el usuario: " + response.getStatusCode());
			}

		}

		return ("Ok...");

	}

	public String delete(String id) {

		String uri = UrisConfig.getEndpoint_deleteRoleUser() + id;

		ResponseRest response = restTemplateRoles.getForObject(uri, ResponseRest.class);

		// check response
		if (response.getCodigo().equals("001")) {
			MessageService.addMessage("Se eliminó el rol: " + id + " con éxito");
			this.loadRolesUser();
		} else {
			MessageService.addMessage("Ocurrió un error eliminando el rol: " + response.getMensaje());
		}

		return ("Ok...");
	}

	public Map<String, Integer> getRoles() {
		return roles;
	}

	public Rol[] obtenerListaRoles() {

		Rol[] result = restTemplateRoles.getForObject(UrisConfig.getEndpoint_getListRoles(), Rol[].class);

		return result;

	}

	public ListRolXUser[] getRolesUser() {

		String uri = UrisConfig.getEndpoint_getListRolesUser() + this.document;

		ListRolXUser[] result = restTemplateRoles.getForObject(uri, ListRolXUser[].class);

		return result;

	}

	public void loadRolesUser() {
		ListRolXUser[] listRoles = this.getRolesUser();
		this.listRolXUser = new ArrayList<ListRolXUser>();

		for (ListRolXUser masterListRolesXUser : listRoles) {
			this.listRolXUser.add(masterListRolesXUser);
		}

	}
	
	public void loadRolesUser(String document) {
		if (document.equals("")) {
			System.out.println("Se requiere numero de documento para cargar la lista de roles");
		} else {
			this.document = document;
			this.loadRolesUser();
			System.out.println("Se cargó con exito la lista de roles del usuario: " + document);
		}
	}

	private Map<String, Integer> roles = new HashMap<String, Integer>();

	@PostConstruct
	public void init() {

		// Lista de Roles
		Rol[] listaRoles = this.obtenerListaRoles();
		roles = new HashMap<String, Integer>();

		for (Rol masterRol : listaRoles) {
			// System.out.println(masterRol.getRole());
			roles.put(masterRol.getRole(), masterRol.getId());
		}

	}

}
