package com.ues.fia.bad115.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ues.fia.bad115.clase.Rol;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
    Rol findByRol(String rol);

    @Query("SELECT r FROM Rol r WHERE lower(r.rol) LIKE lower(concat('%', :searchTerm, '%'))")
    List<Rol> search(String searchTerm);
    
}
