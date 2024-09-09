package com.katsuki.administracintributariav2.model;

public class Task {
  String tarea, ubicacion, descripcion;
  public Task(){};

  public Task(String tarea, String ubicacion, String descripcion) {
    this.tarea = tarea;
    this.ubicacion = ubicacion;
    this.descripcion = descripcion;
  }

  public String getTarea() {
    return tarea;
  }

  public void setTarea(String tarea) {
    this.tarea = tarea;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
