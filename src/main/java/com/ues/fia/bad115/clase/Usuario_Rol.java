package com.ues.fia.bad115.clase;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario_Rol")
public class Usuario_Rol {
    @Id
    @Column(name = "idusuario")
    private int idusuario;
    @ManyToOne
    @JoinColumn(name = "idrol")
    private Rol rol;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public Rol getIdrol() {
        return rol;
    }

    public void setIdrol(Rol rol) {
        this.rol = rol;
    }

}
