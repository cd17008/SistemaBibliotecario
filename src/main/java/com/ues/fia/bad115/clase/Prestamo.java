package com.ues.fia.bad115.clase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Prestamo")
public class Prestamo {
    @Id
    @Column(name = "idprestamo")
    private int id;
    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "idrecurso")
    private Recurso recurso;
    @Column(name = "fechaprestamo")
    private Date fecha;
    @Column(name = "estadoprestamo")
    private int estado;

    @OneToMany(mappedBy = "prestamo")
    private List<Mora> moras;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
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

    public List<Mora> getMoras() {
        return moras;
    }

    public void setMoras(List<Mora> moras) {
        this.moras = moras;
    }

}
