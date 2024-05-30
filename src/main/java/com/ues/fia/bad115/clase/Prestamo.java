package com.ues.fia.bad115.clase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "Prestamo")
public class Prestamo {
    @Id
    @Column(name = "idprestamo")
    private int id;
    @Column(name = "idusuario")
    private int miembro;
    @Column(name = "idrecurso")
    private String recurso;
    @Column(name = "fechaprestamo")
    private Date fecha;
    @Column(name = "estadoprestamo")
    private int estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMiembro() {
        return miembro;
    }

    public void setMiembro(int miembro) {
        this.miembro = miembro;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
