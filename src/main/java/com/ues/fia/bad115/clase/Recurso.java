package com.ues.fia.bad115.clase;

import jakarta.persistence.*;

@Entity
@Table(name="Recurso")
public class Recurso {
    @Id
    @Column(name="RecursoId")
    private String id;
    @Column(name="IdiomaId")
    private String idioma;
    @Column(name="AutorId")
    private int autor;
    @Column(name="CategoryId")
    private int categoria;
    @Column(name="Titulo")
    private String titulo;
    @Column(name="Descripcion")
    private String descripcion;
    @Column(name="Cantidad")
    private int cantidad;
    @Lob
    @Column(name="Cover")
    private byte[] cover;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
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
}
