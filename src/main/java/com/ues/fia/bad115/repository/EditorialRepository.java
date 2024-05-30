package com.ues.fia.bad115.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ues.fia.bad115.clase.Editorial;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long> {
    Editorial findByNombre(String name);

    @Query("SELECT e FROM Editorial e WHERE lower(e.nombre) LIKE lower(concat('%', :searchTerm, '%'))")
    List<Editorial> search(@Param("searchTerm") String searchTerm);
}
