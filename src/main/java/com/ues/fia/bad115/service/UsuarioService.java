package com.ues.fia.bad115.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ues.fia.bad115.repository.UsuarioRepository;
import com.ues.fia.bad115.clase.Carnet;
import com.ues.fia.bad115.clase.Usuario;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        Carnet carnet1 = new Carnet();
        carnet1.setId(carnet);
        return (Usuario) repository.findByCarnet(carnet1);
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
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
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

    // Validacion
    public boolean validarUsuario(Usuario usuario) {
        Usuario usuario1 = repository.findByEmail(usuario.getEmail());
        if (usuario1 != null) {
            if (passwordEncoder.matches(usuario.getPassword(), usuario1.getPassword())) {
                return true;
            }
        }
        return false;
    }

}
