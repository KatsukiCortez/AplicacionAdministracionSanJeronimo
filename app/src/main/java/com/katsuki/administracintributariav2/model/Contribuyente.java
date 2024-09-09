package com.katsuki.administracintributariav2.model;

public class Contribuyente {
  String actEconomica, adeudados, area, correo, direccion, documento, estCivil, fiscalizacion, historial, multa, nombre, pagos, predios, propiedad, telefono, ubicacion, uso, valor, vencimiento;

  public Contribuyente(){};

  public Contribuyente(String actEconomica, String adeudados, String area, String correo, String direccion, String documento, String estCivil, String fiscalizacion, String historial, String multa, String nombre, String pagos, String predios, String propiedad, String telefono, String ubicacion, String uso, String valor, String vencimiento) {
    this.actEconomica = actEconomica;
    this.adeudados = adeudados;
    this.area = area;
    this.correo = correo;
    this.direccion = direccion;
    this.documento = documento;
    this.estCivil = estCivil;
    this.fiscalizacion = fiscalizacion;
    this.historial = historial;
    this.multa = multa;
    this.nombre = nombre;
    this.pagos = pagos;
    this.predios = predios;
    this.propiedad = propiedad;
    this.telefono = telefono;
    this.ubicacion = ubicacion;
    this.uso = uso;
    this.valor = valor;
    this.vencimiento = vencimiento;
  }

  public String getActEconomica() {
    return actEconomica;
  }

  public void setActEconomica(String actEconomica) {
    this.actEconomica = actEconomica;
  }

  public String getAdeudados() {
    return adeudados;
  }

  public void setAdeudados(String adeudados) {
    this.adeudados = adeudados;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
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

  public String getDocumento() {
    return documento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public String getEstCivil() {
    return estCivil;
  }

  public void setEstCivil(String estCivil) {
    this.estCivil = estCivil;
  }

  public String getFiscalizacion() {
    return fiscalizacion;
  }

  public void setFiscalizacion(String fiscalizacion) {
    this.fiscalizacion = fiscalizacion;
  }

  public String getHistorial() {
    return historial;
  }

  public void setHistorial(String historial) {
    this.historial = historial;
  }

  public String getMulta() {
    return multa;
  }

  public void setMulta(String multa) {
    this.multa = multa;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPagos() {
    return pagos;
  }

  public void setPagos(String pagos) {
    this.pagos = pagos;
  }

  public String getPredios() {
    return predios;
  }

  public void setPredios(String predios) {
    this.predios = predios;
  }

  public String getPropiedad() {
    return propiedad;
  }

  public void setPropiedad(String propiedad) {
    this.propiedad = propiedad;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

  public String getUso() {
    return uso;
  }

  public void setUso(String uso) {
    this.uso = uso;
  }

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  public String getVencimiento() {
    return vencimiento;
  }

  public void setVencimiento(String vencimiento) {
    this.vencimiento = vencimiento;
  }
}
