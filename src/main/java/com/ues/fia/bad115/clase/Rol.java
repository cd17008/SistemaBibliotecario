package com.ues.fia.bad115.clase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Rol")
public class Rol {
    @Id
    @Column(name = "idrol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "rol")
    private String rol;

    @OneToMany(mappedBy = "rol")
    private List<Usuario_Rol> usuario_rol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<Usuario_Rol> getUsuario_rol() {
        return usuario_rol;
    }

    public void setUsuario_rol(List<Usuario_Rol> usuario_rol) {
        this.usuario_rol = usuario_rol;
    }

}
