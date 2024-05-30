package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Rol;
import com.ues.fia.bad115.clase.Usuario_Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRolRepository extends JpaRepository<Usuario_Rol, Long> {
    Usuario_Rol findByIdusuario(int id);

    List<Usuario_Rol> findByRol(Rol rol);
}
