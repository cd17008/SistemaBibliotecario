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
    @Column(name="moraid")
    private int id;
    @Column(name="prestamoid")
    private int prestamo;
    @Column(name="fecha")
    private Date fecha;
    @Column(name="monto")
    private float monto;

    public int getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(int prestamo) {
        this.prestamo = prestamo;
    }

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
}
