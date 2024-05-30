package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Idioma;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {
    Idioma findByIdioma(String idioma);

    @Query("SELECT i from Idioma i where lower(i.idioma)LIKE lower(concat('%', :searchTerm, '%'))")
    List<Idioma> search(@Param("searchTerm") String searchTerm);
}
