package com.ues.fia.bad115.clase;

import jakarta.persistence.*;

@Entity
@Table(name = "Recurso")
public class Recurso {
    @Id
    @Column(name = "idrecurso", nullable = true)
    private String id;
    @ManyToOne
    @JoinColumn(name = "idautor")
    private Autor autor;//
    @Column(name = "titulorecurso")
    private String titulo;//
    @ManyToOne
    @JoinColumn(name = "ididioma")
    private Idioma idioma;//
    @ManyToOne
    @JoinColumn(name = "Id_Editorial")
    private Editorial editorial;
    @ManyToOne
    @JoinColumn(name = "Subcategoria")
    private Subcategoria subcategoria;//
    @Column(name = "Cantidad_Disponible")
    private int cantidad;
    @Column(name = "Precio")
    private float precio;
    @Column(name = "Descripcion_Recurso")
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

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
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

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
