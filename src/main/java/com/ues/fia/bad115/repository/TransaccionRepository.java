package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Transaccion;
import com.ues.fia.bad115.clase.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    Transaccion findById(int id);

    List<Transaccion> findByTipo(String tipo);

    List<Transaccion> findByFecha(Date fecha);

    List<Transaccion> findByMonto(float monto);

    List<Transaccion> findByUsuario(Usuario idusuario);

    @Query("SELECT t FROM Transaccion t WHERE t.monto >= :searchTerm")
    List<Transaccion> searchByMontoMayor(@Param("searchTerm") float searchTerm);

    @Query("SELECT t FROM Transaccion t WHERE t.monto <= :searchTerm")
    List<Transaccion> searchByMontoMenor(@Param("searchTerm") float searchTerm);

    @Query("SELECT t FROM Transaccion t WHERE t.fecha >= :searchTerm")
    List<Transaccion> searchByFechaMayor(@Param("searchTerm") Date searchTerm);

    @Query("SELECT t FROM Transaccion t WHERE t.fecha <= :searchTerm")
    List<Transaccion> searchByFechaMenor(@Param("searchTerm") Date searchTerm);


}
