package com.ues.fia.bad115.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ues.fia.bad115.repository.UsuarioRepository;
import com.ues.fia.bad115.clase.Usuario;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    // Métodos GET
    public List<Usuario> getUsuarios() {
        return repository.findAll();
    }

    public Usuario getUsuario(int id) {
        return (Usuario) repository.findById((long) id).orElse(null);
    }

    public Usuario getUsuarioByEmail(String email) {
        return (Usuario) repository.findByEmail(email);
    }

    public Usuario getUsuarioByCarnet(String carnet) {
        return (Usuario) repository.findByCarnet(carnet);
    }

    public Usuario getUsuarioByNombre(String nombre) {
        return (Usuario) repository.findByNombre(nombre);
    }

    public Usuario getUsuarioByApellido(String apellido) {
        return (Usuario) repository.findByApellido(apellido);
    }

    public Usuario getUsuarioByTelefono(String telefono) {
        return (Usuario) repository.findByTelefono(telefono);
    }

    public List<Usuario> getUsuariosByTipoUsuario(String tipousuario) {
        return repository.findByTipousuario(tipousuario);
    }

    public List<Usuario> getUsuariosByActivo(int activo) {
        return repository.findByActivo(activo);
    }

    public List<Usuario> searchByName(String searchTerm) {
        return repository.searchByName(searchTerm);
    }


    // Métodos POST
    public Usuario saveUsuario(Usuario usuario) {
        return (Usuario) repository.save(usuario);
    }

    public List<Usuario> saveUsuarios(List<Usuario> usuarios) {
        return repository.saveAll(usuarios);
    }

    // Métodos UPDATE
    public Usuario updateUsuario(Usuario newUsuario) {
        Usuario usuario = (Usuario) repository.findById((long) newUsuario.getId()).orElse(null);
        if (newUsuario.getNombre() != null) {
            usuario.setNombre(newUsuario.getNombre());
        }
        if (newUsuario.getApellido() != null) {
            usuario.setApellido(newUsuario.getApellido());
        }
        if (newUsuario.getEmail() != null) {
            usuario.setEmail(newUsuario.getEmail());
        }
        if (newUsuario.getTelefono() != null) {
            usuario.setTelefono(newUsuario.getTelefono());
        }
        if (newUsuario.getCarnet() != null) {
            usuario.setCarnet(newUsuario.getCarnet());
        }
        if (newUsuario.getTipousuario() != null) {
            usuario.setTipousuario(newUsuario.getTipousuario());
        }
        if (newUsuario.getActivo() != usuario.getActivo()) {
            usuario.setActivo(newUsuario.getActivo());
        }
        return (Usuario) repository.save(usuario);
    }

    // Métodos DELETE
    public String deleteUsuario(int id) {
        repository.deleteById((long) id);
        return "Usuario eliminado correctamente: " + id;
    }

}
