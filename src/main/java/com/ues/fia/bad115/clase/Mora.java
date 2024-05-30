package com.ues.fia.bad115.clase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="Mora")
public class Mora {
    @Id
    @Column(name="idmora")
    private int id;
    @Column(name="cantidad")
    private float cantidad;
    @Column(name="idprestamo")
    private int idprestamo;
    @Column(name="estadomora")
    private int estado;
    @Column(name="fechamora")
    private Date fechamora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(int idprestamo) {
        this.idprestamo = idprestamo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechamora() {
        return fechamora;
    }

    public void setFechamora(Date fechamora) {
        this.fechamora = fechamora;
    }

}
