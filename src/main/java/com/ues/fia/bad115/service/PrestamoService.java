package com.ues.fia.bad115.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ues.fia.bad115.clase.Prestamo;
import com.ues.fia.bad115.repository.PrestamoRepository;

import java.util.List;

@Service
public class PrestamoService {
    @Autowired 
    private PrestamoRepository repository;

    // Métodos GET
    public List<Prestamo> getPrestamos() {
        return repository.findAll();
    }

    public Prestamo getPrestamo(int id) {
        return (Prestamo) repository.findById((long) id).orElse(null);
    }

    // Métodos POST
    public Prestamo savePrestamo(Prestamo prestamo) {
        return (Prestamo) repository.save(prestamo);
    }

    public List<Prestamo> savePrestamos(List<Prestamo> prestamos) {
        return repository.saveAll(prestamos);
    }

    // Métodos UPDATE
    public Prestamo updatePrestamo(Prestamo newPrestamo) {
        Prestamo prestamo = (Prestamo) repository.findById((long)newPrestamo.getId()).orElse(null);
        if (newPrestamo.getRecurso() != null) {
            prestamo.setRecurso(newPrestamo.getRecurso());
        }
        if (newPrestamo.getFecha() != null) {
            prestamo.setFecha(newPrestamo.getFecha());
        }
        if (newPrestamo.getEstado() != prestamo.getEstado()) {
            prestamo.setEstado(newPrestamo.getEstado());
        }
        return (Prestamo) repository.save(prestamo);
    }

    // Métodos DELETE
    public String deletePrestamo(int id) {
        repository.deleteById((long) id);
        return "Prestamo eliminado correctamente";
    }
}
