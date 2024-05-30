package com.ues.fia.bad115.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ues.fia.bad115.clase.Autor;
import com.ues.fia.bad115.clase.Subcategoria;
import java.util.List;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {
    Autor findByNombre(String name);

    @Query("SELECT s FROM Subcategoria s WHERE lower(s.nombre) LIKE lower(concat('%', :searchTerm, '%'))")
    List<Subcategoria> search(@Param("searchTerm") String searchTerm);

}
