package com.ues.fia.bad115.clase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="Prestamo")
public class Prestamo {
    @Id
    @Column(name="prestamoid")
    private int id;
    @Column(name="miembroid")
    private int miembro;
    @Column(name="recursoid")
    private String recurso;
    @Column(name="fechaprestamo")
    private Date prestamo;
    @Column(name="fechadevolucion")
    private Date devolucion;
    @Column(name="estado")
    private String estado;

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

    public Date getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Date prestamo) {
        this.prestamo = prestamo;
    }

    public Date getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(Date devolucion) {
        this.devolucion = devolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
