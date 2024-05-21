package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Carnet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarnetRepository extends JpaRepository<Carnet, Long> {

}
