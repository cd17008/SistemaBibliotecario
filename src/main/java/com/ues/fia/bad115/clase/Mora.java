package com.ues.fia.bad115.clase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

import java.util.Date;

@Entity
@Table(name = "Mora")
public class Mora {
    @Id
    @Column(name = "idmora")
    private int id;
    @Column(name = "cantidad")
    private float cantidad;
    @ManyToOne
    @JoinColumn(name = "idprestamo")
    private Prestamo prestamo;
    @Column(name = "estadomora")
    private int estado;
    @Column(name = "fechamora")
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

    public Prestamo getIdprestamo() {
        return prestamo;
    }

    public void setIdprestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
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
