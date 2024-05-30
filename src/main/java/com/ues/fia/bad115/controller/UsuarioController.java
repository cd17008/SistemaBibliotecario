package com.ues.fia.bad115.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.ues.fia.bad115.clase.Usuario;
import com.ues.fia.bad115.service.UsuarioService;

import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping("/addusuario")
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return service.saveUsuario(usuario);
    }

    @GetMapping("/usuario")
    public List<Usuario> findAllUsuarios() {
        return service.getUsuarios();
    }

    @GetMapping("/usuario/{id}")
    public Usuario findUsuarioById(@PathVariable int id) {
        return service.getUsuario(id);
    }

    @GetMapping("/usuario/email/{email}")
    public Usuario findUsuarioByEmail(@PathVariable String email) {
        return service.getUsuarioByEmail(email);
    }

    @GetMapping("/usuario/carnet/{carnet}")
    public Usuario findUsuarioByCarnet(@PathVariable String carnet) {
        return service.getUsuarioByCarnet(carnet);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public Usuario findUsuarioByNombre(@PathVariable String nombre) {
        return service.getUsuarioByNombre(nombre);
    }

    @GetMapping("/usuario/apellido/{apellido}")
    public Usuario findUsuarioByApellido(@PathVariable String apellido) {
        return service.getUsuarioByApellido(apellido);
    }

    @GetMapping("/usuario/telefono/{telefono}")
    public Usuario findUsuarioByTelefono(@PathVariable String telefono) {
        return service.getUsuarioByTelefono(telefono);
    }

    @GetMapping("/usuario/tipousuario/{tipousuario}")
    public List<Usuario> findUsuariosByTipoUsuario(@PathVariable String tipousuario) {
        return service.getUsuariosByTipoUsuario(tipousuario);
    }

    @GetMapping("/usuario/activo/{activo}")
    public List<Usuario> findUsuariosByActivo(@PathVariable int activo) {
        return service.getUsuariosByActivo(activo);
    }

    @GetMapping("/usuario/search/{searchTerm}")
    public List<Usuario> searchByName(@PathVariable String searchTerm) {
        return service.searchByName(searchTerm);
    }

    @DeleteMapping("/delusuario/{id}")
    public String deleteUsuario(@PathVariable int id) {
        return service.deleteUsuario(id);
    }

    @PutMapping("/updateusuario")
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        return service.updateUsuario(usuario);
    }

    @PutMapping("/updateusuario/{id}")
    public Usuario updateUsuarioById(@PathVariable int id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return service.updateUsuario(usuario);
    }
}
