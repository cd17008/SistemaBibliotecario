package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, String> {
    Recurso findByTitulo(String titulo);

    Recurso findByAutor(int autor);

    Recurso findBySubcategoria(int subcategoria);

    @Query("SELECT r FROM Recurso r WHERE lower(r.titulo) LIKE lower(concat('%', :searchTerm, '%'))")
    List<Recurso> search(@Param("searchTerm") String searchTerm);
}
