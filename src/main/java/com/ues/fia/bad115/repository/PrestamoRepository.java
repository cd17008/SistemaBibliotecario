package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Prestamo;
import com.ues.fia.bad115.clase.Recurso;
import com.ues.fia.bad115.clase.Usuario;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    Prestamo findById(int id);

    List<Prestamo> findByUsuario(Usuario usuario);

    Prestamo findByRecurso(Recurso recurso);

    Prestamo findByFecha(Date fecha);

    Prestamo findByDevolucion(Date devolucion);

    

}
