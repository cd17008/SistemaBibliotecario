package com.ues.fia.bad115.clase;

import jakarta.persistence.*;

@Entity
@Table(name = "Recurso")
public class Recurso {
    @Id
    @Column(name = "idrecurso", nullable = true)
    private String id;
    @Column(name = "idautor")
    private int autor;//
    @Column(name = "titulorecurso")
    private String titulo;//
    @Column(name = "ididioma")
    private int idioma;//
    @Column(name = "IdEditorial")
    private int editorial;
    @Column(name = "Subcategoria")
    private int subcategoria;//
    @Column(name = "CantidadDisponible")
    private int cantidad;
    @Column(name = "Precio")
    private float precio;
    @Column(name = "DescripcionRecurso")
    private String descripcion;//
    @Lob
    @Column(name = "portada")
    private byte[] cover;
    @Column(name = "Calificacion")
    private float calificacion;
    @Column(name = "Publicacion")
    private int publicacion;

    public Recurso() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdioma() {
        return idioma;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public int getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(int subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public int getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    public int getEditorial() {
        return editorial;
    }

    public void setEditorial(int editorial) {
        this.editorial = editorial;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
