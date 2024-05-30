package com.ues.fia.bad115.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ues.fia.bad115.clase.Rol;
import com.ues.fia.bad115.repository.RolRepository;

import java.util.List;

@Service
public class RolService {
    @Autowired
    private RolRepository repository;

    // Métodos GET
    public List<Rol> getRoles() {
        return repository.findAll();
    }

    public List<Rol> findRoles(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.search(filtro);
        }
    }

    public Rol getRol(int id) {
        return (Rol) repository.findById((long) id).orElse(null);
    }

    // Métodos POST

    public Rol saveRol(Rol rol) {
        return (Rol) repository.save(rol);
    }

    public List<Rol> saveRoles(List<Rol> roles) {
        return repository.saveAll(roles);
    }

    // Métodos UPDATE
    public Rol updateRol(Rol newRol) {
        Rol rol = (Rol) repository.findById((long)newRol.getId()).orElse(null);
        if (newRol.getRol() != null) {
            rol.setRol(newRol.getRol());
        }
        return (Rol) repository.save(rol);
    }

    // Métodos DELETE
    public String deleteRol(int id) {
        repository.deleteById((long) id);
        return "Rol eliminado correctamente";
    }
}
