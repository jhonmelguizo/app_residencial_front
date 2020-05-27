package com.app.residencial.front.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.residencial.front.config.GlobalUtil;
import com.app.residencial.front.config.UrisConfig;
import com.app.residencial.front.entities.Property;
import com.app.residencial.front.entities.PropertyxUser;
import com.app.residencial.front.entities.ResponseRest;
import com.app.residencial.front.entities.Rol;
import com.app.residencial.front.entities.TypeProperty;
import com.app.residencial.front.entities.User;
import com.app.residencial.front.services.MessageService;

@Named("associateproperty")
public class AssociateProperty {

	private String name;
	private int idtypeproperty;
	private String document;
	private Map<String, Integer> listtypeproperty = new HashMap<String, Integer>();
	private List<PropertyxUser> listproxuser;

	@Autowired
	@Qualifier("restTemplateProperty")
	private RestTemplate restTemplateProperty;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdtypeproperty() {
		return idtypeproperty;
	}

	public void setIdtypeproperty(int idtypeproperty) {
		this.idtypeproperty = idtypeproperty;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Map<String, Integer> getListtypeproperty() {
		return listtypeproperty;
	}

	public void setListtypeproperty(Map<String, Integer> listtypeproperty) {
		this.listtypeproperty = listtypeproperty;
	}
	
	
	
	



	public List<PropertyxUser> getListproxuser() {
		return listproxuser;
	}

	public void setListproxuser(List<PropertyxUser> listproxuser) {
		this.listproxuser = listproxuser;
	}

	@Bean
	@Qualifier("restTemplateProperty")
	public RestTemplate restTemplateProperty() {
		return new RestTemplate();
	}

	@PostConstruct
	public void init() {
		TypeProperty[] listype = this.getlistypeproperty();
		listtypeproperty = new HashMap<String, Integer>();

		for (TypeProperty mastertypeproperty : listype) {

			listtypeproperty.put(mastertypeproperty.getName(), mastertypeproperty.getId());
		}
		
		this.listproxuser =new ArrayList<PropertyxUser>();
		PropertyxUser[] listproxuser = this.getlistpropertyxuser("3442");
		for (PropertyxUser masterproxuser : listproxuser) {

			System.out.print("Paso por lista");
			this.listproxuser.add(masterproxuser);
		}
		
		System.out.print("Esta es la cantidad"+this.listproxuser.size());
		
	
		
		

	}

	public TypeProperty[] getlistypeproperty() {

		TypeProperty[] result = restTemplateProperty.getForObject(UrisConfig.getEndpoint_getPropertiesType(),
				TypeProperty[].class);

		return result;

	}
	
	public PropertyxUser[] getlistpropertyxuser(String document) {
		String ruta=UrisConfig.getEndpoint_getListPropertyxUser()+document;
		System.out.print("ruta"+ruta);
		PropertyxUser[] result =  restTemplateProperty.getForObject(ruta,PropertyxUser[].class);
		
		return result;

	}

	public String addproperty(String document) {

		System.out.print("este es el id"+document+ "Este es el idproperty"+ this.idtypeproperty+ "name"+this.name);
		Property property = new Property(this.name, this.idtypeproperty, document);
		// build the request
		HttpEntity<Property> request = new HttpEntity<>(property, GlobalUtil.getheaders());

		// send POST request
		ResponseEntity<ResponseRest> response = restTemplateProperty.postForEntity(UrisConfig.getEndpoint_createProperty(), request,
				ResponseRest.class);
		
		// check response
				if (response.getStatusCode() == HttpStatus.OK) {
					System.out.println("Post Created");
					System.out.println(response.getBody().getMensaje());
					System.out.println(response.getBody().getCodigo());
					this.setdatagrid(document);
					MessageService.addMessage(response.getBody().getMensaje());
					
				
				} else {
					System.out.println("Request Failed");
					System.out.println(response.getStatusCode());
					MessageService.addMessage(response.getBody().getMensaje());
				}
			
				return ("Ok...");
		
	

	}
	
	public void setdatagrid(String document)
	{
		
		this.listproxuser =new ArrayList<PropertyxUser>();
		PropertyxUser[] listproxuser = this.getlistpropertyxuser(document);
		for (PropertyxUser masterproxuser : listproxuser) {

			System.out.print("Paso por lista");
			this.listproxuser.add(masterproxuser);
		}
		
	}

}
