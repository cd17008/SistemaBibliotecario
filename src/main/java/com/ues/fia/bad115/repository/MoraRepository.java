package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Mora;
import com.ues.fia.bad115.clase.Prestamo;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MoraRepository extends JpaRepository<Mora, Long> {
    Mora findById(int id);

    Mora findByCantidad(float cantidad);

    Mora findByPrestamo(Prestamo prestamo);

    Mora findByEstado(int estado);

    Mora findByFechamora(Date fechamora);

    @Query("SELECT m FROM Mora m WHERE m.cantidad >= :searchTerm")
    List<Mora> searchUp(@Param("searchTerm") float searchTerm);

    @Query("SELECT m FROM Mora m WHERE m.cantidad <= :searchTerm")
    List<Mora> searchDown(@Param("searchTerm") float searchTerm);
}
