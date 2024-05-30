package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Prestamo;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    Prestamo findById(int id);

    Prestamo findByMiembro(int miembro);

    Prestamo findByRecurso(String recurso);

    Prestamo findByFecha(Date fecha);

    Prestamo findByEstado(int estado);

}
