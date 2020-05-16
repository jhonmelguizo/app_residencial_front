package com.app.residencial.front.entities;

/*
 * @jhonmelguizo
 * Clase para recibir las respuestas genericas de los servicios rest.
 */
public class ResponseRest {

	private String codigo;
	private String mensaje;
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
