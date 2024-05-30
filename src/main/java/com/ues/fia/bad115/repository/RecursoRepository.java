package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Autor;
import com.ues.fia.bad115.clase.Recurso;
import com.ues.fia.bad115.clase.Subcategoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, String> {
    Optional<Recurso> findById(String id);

    Recurso findByTitulo(String titulo);

    List<Recurso> findByAutor(Autor autor);

    List<Recurso> findBySubcategoria(Subcategoria subcategoria);

    @Query("SELECT r FROM Recurso r WHERE lower(r.titulo) LIKE lower(concat('%', :searchTerm, '%'))")
    List<Recurso> search(@Param("searchTerm") String searchTerm);
}
