package com.ues.fia.bad115.clase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

import java.util.List;

@Entity
@Table(name = "Idioma")
public class Idioma {
    @Id
    @Column(name = "ididioma")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idioma")
    private String idioma;

    @OneToMany(mappedBy = "idioma")
    private List<Recurso> recursos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }
}
