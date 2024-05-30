package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import com.ues.fia.bad115.clase.Carnet;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findByEmail(String email);
    Usuario findByCarnet(Carnet carnet);
    Usuario findByNombre(String nombre);
    Usuario findByApellido(String apellido);
    Usuario findByTelefono(String telefono);
    List<Usuario> findByTipousuario(String tipousuario);
    List<Usuario> findByActivo(int activo);

    @Query("SELECT u FROM Usuario u WHERE lower(u.nombre) LIKE lower(concat('%', :searchTerm, '%')) or lower(u.apellido) LIKE lower(concat('%', :searchTerm, '%'))")
    List<Usuario> searchByName(@Param("searchTerm") String searchTerm);
}
