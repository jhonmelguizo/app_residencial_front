package com.app.residencial.front.config;

public class UrisConfig {
	
	private static String url_server = "http://localhost:8080";
	
	private static String endpoint_createUser = "/api/createuser";
	private static String endpoint_getListRoles = "/api/role";



	public static String getEndpoint_getListRoles() {
		return url_server + endpoint_getListRoles;
	}

	public static void setEndpoint_getListRoles(String endpoint_getListRoles) {
		UrisConfig.endpoint_getListRoles = endpoint_getListRoles;
	}

	public static String getUrl_server() {
		return url_server;
	}

	public static void setUrl_server(String url_server) {
		UrisConfig.url_server = url_server;
	}

	public static String getEndpoint_createUser() {
		return url_server + endpoint_createUser;
	}

	public static void setEndpoint_createUser(String endpoint_createUser) {
		UrisConfig.endpoint_createUser = endpoint_createUser;
	}



}