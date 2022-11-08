package com.paledot01.shoesformen.models;

import com.google.firebase.database.Exclude;

public class Calzado {

    private String id;
    private String marca;
    private String modelo;
    private String talla;
    private String precio;
    private String descripcion;
    private String url; // se guarda la direccion(url) donde se encuentra la imagen.

    public Calzado() {
    }

    public Calzado(String marca, String modelo, String talla, String precio, String descripcion, String url) {
//        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.talla = talla;
        this.precio = precio;
        this.descripcion = descripcion;
        this.url = url;
    }

    @Exclude
    public String getId() {
        return id;
    }
    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
