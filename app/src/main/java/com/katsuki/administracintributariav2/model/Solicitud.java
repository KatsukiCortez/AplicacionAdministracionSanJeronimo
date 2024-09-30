package com.katsuki.administracintributariav2.model;

public class Solicitud {
  private String dni;
  private String nombre;
  private String apellidos;
  private String telefono;
  private String correo;
  private String direccion;
  private String datoContribuyente;
  private String referencia;
  private String imagen1;
  private String imagen2;
  private String plano;

  public Solicitud() {
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getDatoContribuyente() {
    return datoContribuyente;
  }

  public void setDatoContribuyente(String datoContribuyente) {
    this.datoContribuyente = datoContribuyente;
  }

  public String getReferencia() {
    return referencia;
  }

  public void setReferencia(String referencia) {
    this.referencia = referencia;
  }

  public String getImagen1() {
    return imagen1;
  }

  public void setImagen1(String imagen1) {
    this.imagen1 = imagen1;
  }

  public String getImagen2() {
    return imagen2;
  }

  public void setImagen2(String imagen2) {
    this.imagen2 = imagen2;
  }

  public String getPlano() {
    return plano;
  }

  public void setPlano(String plano) {
    this.plano = plano;
  }
}