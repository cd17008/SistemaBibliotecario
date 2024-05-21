package com.ues.fia.bad115.repository;

import com.ues.fia.bad115.clase.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, Long> {
}
