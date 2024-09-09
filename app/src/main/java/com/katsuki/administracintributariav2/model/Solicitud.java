package com.katsuki.administracintributariav2.model;

public class Solicitud {
  private String dni;
  private String nombre;
  private String apellidos;
  private String telefono;
  private String correo;
  private String direccion;
  private String datoContribuyente;
  private String referenciaLlegada;
  private String imagenDomi1;
  private String imagenDomi2;
  private String imagenCroquis;

  public Solicitud() {
  }

  public Solicitud(String dni, String nombre, String apellidos, String telefono, String correo, String direccion, String datoContribuyente, String referenciaLlegada, String imagenDomi1, String imagenDomi2, String imagenCroquis) {
    this.dni = dni;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.telefono = telefono;
    this.correo = correo;
    this.direccion = direccion;
    this.datoContribuyente = datoContribuyente;
    this.referenciaLlegada = referenciaLlegada;
    this.imagenDomi1 = imagenDomi1;
    this.imagenDomi2 = imagenDomi2;
    this.imagenCroquis = imagenCroquis;
  }

  // Getters y Setters

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getNombres() {
    return nombre;
  }

  public void setNombres(String nombres) {
    this.nombre = nombres;
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

  public String getReferenciaLlegada() {
    return referenciaLlegada;
  }

  public void setReferenciaLlegada(String referenciaLlegada) {
    this.referenciaLlegada = referenciaLlegada;
  }

  public String getImagenDomi1() {
    return imagenDomi1;
  }

  public void setImagenDomi1(String imagenDomi1) {
    this.imagenDomi1 = imagenDomi1;
  }

  public String getImagenDomi2() {
    return imagenDomi2;
  }

  public void setImagenDomi2(String imagenDomi2) {
    this.imagenDomi2 = imagenDomi2;
  }

  public String getImagenCroquis() {
    return imagenCroquis;
  }

  public void setImagenCroquis(String imagenCroquis) {
    this.imagenCroquis = imagenCroquis;
  }
}