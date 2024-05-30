package com.ues.fia.bad115.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ues.fia.bad115.service.RolService;
import com.ues.fia.bad115.clase.Rol;

import java.util.List;

@RestController
public class RolController {
    @Autowired
    private RolService service;

    @PostMapping("/addrol")
    public Rol addRol(@RequestBody Rol rol) {
        return service.saveRol(rol);
    }

    @GetMapping("/rol")
    public List<Rol> findAllRoles() {
        return service.getRoles();
    }

    @GetMapping("/rol/{id}")
    public Rol findRolById(@PathVariable int id) {
        return service.getRol(id);
    }

    @DeleteMapping("/delrol/{id}")
    public String deleteRol(@PathVariable int id) {
        return service.deleteRol(id);
    }

    @PutMapping("/updaterol")
    public Rol updateRol(@RequestBody Rol rol) {
        return service.updateRol(rol);
    }
}
