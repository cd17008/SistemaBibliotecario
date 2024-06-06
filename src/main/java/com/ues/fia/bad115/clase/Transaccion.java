package com.ues.fia.bad115.clase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;

@Entity
@Table(name = "Transaccion")
public class Transaccion {
    @Id
    @Column(name = "idtransaccion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fechatrans")
    private Date fecha;
    @Column(name = "cantidadtrans")
    private float monto;
    @Column(name = "tipotrans")
    private String tipo;
    @Column(name = "descripciontrans")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getIdusuario() {
        return usuario;
    }

    public void setIdusuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
