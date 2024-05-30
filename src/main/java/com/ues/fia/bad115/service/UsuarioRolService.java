package com.ues.fia.bad115.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ues.fia.bad115.repository.UsuarioRolRepository;
import com.ues.fia.bad115.clase.Usuario_Rol;
import java.util.List;

@Service
public class UsuarioRolService {
    @Autowired
    private UsuarioRolRepository repository;

    // Métodos GET
    public List<Usuario_Rol> getUsuarioRoles() {
        return repository.findAll();
    }

    public Usuario_Rol getUsuario_RolByIdusuario(int id) {
        return (Usuario_Rol) repository.findByIdusuario(id);
    }

    public List<Usuario_Rol> getUsuarioRolIdUsuario(int idrol) {
        return repository.findByIdrol(idrol);
    }

    // Métodos POST
    public Usuario_Rol saveUsuarioRol(Usuario_Rol usuarioRol) {
        return (Usuario_Rol) repository.save(usuarioRol);
    }

    public List<Usuario_Rol> saveUsuarioRoles(List<Usuario_Rol> usuarioRoles) {
        return repository.saveAll(usuarioRoles);
    }

    // Métodos UPDATE
    public Usuario_Rol updateUsuarioRol(Usuario_Rol newUsuarioRol) {
        Usuario_Rol usuarioRol = (Usuario_Rol) repository.findById((long) newUsuarioRol.getIdusuario()).orElse(null);
        if (newUsuarioRol.getIdrol() != usuarioRol.getIdrol()) {
            usuarioRol.setIdrol(newUsuarioRol.getIdrol());
        }
        return (Usuario_Rol) repository.save(usuarioRol);
    }

    // Métodos DELETE
    public String deleteUsuarioRol(int id) {
        repository.deleteById((long) id);
        return "UsuarioRol eliminado correctamente: " + id;
    }
}
