package com.app.residencial.frontResidencial.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("loginbean")
public class LoginBean implements Serializable {

 private static final long serialVersionUID = 1L;

 private String message;

 public String getMessage() {
  return message;
 }

 public void setMessage(String message) {
  this.message = message;
 }

 private String username = "";
 private String password = "";

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String login(String type) {

  if (this.username.isEmpty() == false && this.username.equalsIgnoreCase("naldo")
    && this.password.isEmpty() == false && this.password.equals("123")) {
   return "success";
  }
  return "error";
 }

 public String getAccion() {

  FacesContext facesContext = FacesContext.getCurrentInstance();
  Boolean esPostback = facesContext.isPostback();

  if (esPostback) { // solo si hay una accion
   if (this.username.equals("111")) {
    return "success";
   } else
    return "error";
  }
  else
  return ""; // primer request no hace nada
 }
}