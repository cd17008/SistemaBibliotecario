package com.ues.fia.bad115.clase;

import jakarta.persistence.*;

@Entity
@Table(name="Usuario_Rol")
public class Usuario_Rol {
    @Id
    @Column(name="idusuario")
    private int idusuario;
    @Column(name="idrol")
    private int idrol;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }
    
}
