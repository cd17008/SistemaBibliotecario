package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String name);

    @Query("SELECT a FROM Autor a WHERE lower(a.nombre) LIKE lower(concat('%', :searchTerm, '%')) or lower(a.apellido)LIKE lower(concat('%', :searchTerm, '%')) or lower(a.pseudonimo) LIKE lower(concat('%', :searchTerm, '%')) or lower(a.pais) LIKE lower(concat('%', :searchTerm, '%'))")
    List<Autor> search(@Param("searchTerm") String searchTerm);

    @Query("SELECT a FROM Autor a WHERE lower(a.pais) LIKE lower(concat('%', :searchTerm, '%'))")
    List<Autor> searchByCountry(@Param("searchTerm") String searchTerm);
}
