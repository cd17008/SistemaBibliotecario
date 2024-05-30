package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Carnet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarnetRepository extends JpaRepository<Carnet, Long> {
    Carnet findById(String id);

    List<Carnet> findByEstado(int estado);

    @Query("SELECT c FROM Carnet c WHERE c.creacion >= :fechaInicio AND c.creacion <= :fechaFin")
    List<Carnet> findByFechaCreacion(Date fechaInicio, Date fechaFin);


}
