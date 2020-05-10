package com.app.residencial.frontResidencial.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("registrar")
public class RegistrarUsuario {

	private String TipoDocumento = "";
	private String NumeroDocumento;
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

	public String registrar() {
		addMessage("Usuario registrado exitosamente!!");
		return ("Ok");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
