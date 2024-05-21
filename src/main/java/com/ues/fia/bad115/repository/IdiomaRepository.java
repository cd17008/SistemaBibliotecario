package com.ues.fia.bad115.repository;


import com.ues.fia.bad115.clase.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {

}
