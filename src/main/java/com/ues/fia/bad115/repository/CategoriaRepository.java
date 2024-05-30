package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByNombre(String name);

    @Query("SELECT c FROM Categoria c WHERE lower(c.nombre) LIKE lower(concat('%', :searchTerm, '%'))")
    List<Categoria> search(@Param("searchTerm") String searchTerm);
}
