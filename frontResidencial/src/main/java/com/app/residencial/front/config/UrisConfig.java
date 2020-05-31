package com.app.residencial.front.config;

public class UrisConfig {
	
	private static String url_server = "http://localhost:8080";
	
	private static String endpoint_createUser = "/api/createuser";
	private static String endpoint_getListRoles = "/api/role";
	private static String endpoint_getListStatesUser = "/api/getuserstates";
	private static String endpoint_getPropertiesType="/api/getpropertiestype";
	private static String endpoint_createProperty="/api/createproperty";
	private static String endpoint_getListPropertyxUser="/api/propertyxuser/";
	private static String endpoint_associateRoleUser = "/api/insertrole";
	private static String endpoint_getListRolesUser = "/api/rolexuser/";
	private static String endpoint_getUser = "/api/users/";



	public static String getEndpoint_getUser() {
		return url_server + endpoint_getUser;
	}

	public static void setEndpoint_getUser(String endpoint_getUser) {
		UrisConfig.endpoint_getUser = endpoint_getUser;
	}

	public static String getEndpoint_getListRolesUser() {
		return url_server + endpoint_getListRolesUser;
	}

	public static void setEndpoint_getListRolesUser(String endpoint_getListRolesUser) {
		UrisConfig.endpoint_getListRolesUser = endpoint_getListRolesUser;
	}

	public static String getEndpoint_associateRoleUser() {
		return url_server + endpoint_associateRoleUser;
	}

	public static void setEndpoint_associateRoleUser(String endpoint_associateRoleUser) {
		UrisConfig.endpoint_associateRoleUser = endpoint_associateRoleUser;
	}
	public static String getEndpoint_getListStatesUser() {
		return  url_server + endpoint_getListStatesUser;
	}

	public static void setEndpoint_getListStatesUser(String endpoint_getListStatesUser) {
		UrisConfig.endpoint_getListStatesUser = endpoint_getListStatesUser;
	}

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

	public static String getEndpoint_getPropertiesType() {
		return url_server+endpoint_getPropertiesType;
	}

	public static void setEndpoint_getPropertiesType(String endpoint_getPropertiesType) {
		UrisConfig.endpoint_getPropertiesType = endpoint_getPropertiesType;
	}

	public static String getEndpoint_createProperty() {
		return url_server+endpoint_createProperty;
	}

	public static void setEndpoint_createProperty(String endpoint_createProperty) {
		UrisConfig.endpoint_createProperty = endpoint_createProperty;
	}

	public static String getEndpoint_getListPropertyxUser() {
		return url_server+endpoint_getListPropertyxUser;
	}

	public static void setEndpoint_getListPropertyxUser(String endpoint_getListPropertyxUser) {
		UrisConfig.endpoint_getListPropertyxUser = endpoint_getListPropertyxUser;
	}
	
	
	
	
	
	





}
