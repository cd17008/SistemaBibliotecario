package com.ues.fia.bad115.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.ues.fia.bad115.service.UsuarioRolService;
import com.ues.fia.bad115.clase.Usuario_Rol;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioRolController {
    @Autowired
    private UsuarioRolService service;

    @PostMapping("/addusuariorol")
    public Usuario_Rol addUsuarioRol(@RequestBody Usuario_Rol usuarioRol) {
        return service.saveUsuarioRol(usuarioRol);
    }

    @GetMapping("/usuariorol")
    public List<Usuario_Rol> findAllUsuarioRoles() {
        return service.getUsuarioRoles();
    }

    @GetMapping("/usuariorol/{id}")
    public Usuario_Rol findUsuarioRolById(@PathVariable int id) {
        return service.getUsuario_RolByIdusuario(id);
    }

    @GetMapping("/usuariorol/rol/{filtro}")
    public List<Usuario_Rol> findUsuarioRolIdUsuario(@PathVariable int filtro) {
        return service.getUsuarioRolIdUsuario(filtro);
    }

    @DeleteMapping("/delusuariorol/{id}")
    public String deleteUsuarioRol(@PathVariable int id) {
        return service.deleteUsuarioRol(id);
    }

    @PutMapping("/updateusuariorol")
    public Usuario_Rol updateUsuarioRol(@RequestBody Usuario_Rol usuarioRol) {
        return service.updateUsuarioRol(usuarioRol);
    }

}
