package com.ues.fia.bad115.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ues.fia.bad115.repository.MoraRepository;
import com.ues.fia.bad115.clase.Mora;
import com.ues.fia.bad115.clase.Prestamo;

import java.util.List;

@Service
public class MoraService {
    @Autowired
    private MoraRepository repository;

    // Métodos GET
    public List<Mora> getMoras() {
        return repository.findAll();
    }

    public Mora getMora(int id) {
        return (Mora) repository.findById((long) id).orElse(null);
    }

    public Mora findMorasByCantidad(float cantidad) {
        return repository.findByCantidad(cantidad);
    }

    public Mora findMorasByPrestamo(Prestamo prestamo) {
        return repository.findByPrestamo(prestamo);
    }

    public Mora findMorasByEstado(int estado) {
        return repository.findByEstado(estado);
    }

    public Mora findMorasByFechamora(java.sql.Date fechamora) {
        return repository.findByFechamora(fechamora);
    }

    public List<Mora> findMorasUp(float cantidad) {
        return repository.searchUp(cantidad);
    }

    public List<Mora> findMorasDown(float cantidad) {
        return repository.searchDown(cantidad);
    }

    // Métodos POST
    public Mora saveMora(Mora mora) {
        return (Mora) repository.save(mora);
    }

    public List<Mora> saveMoras(List<Mora> moras) {
        return repository.saveAll(moras);
    }

    // Métodos UPDATE
    public Mora updateMora(Mora newMora) {
        Mora mora = (Mora) repository.findById(newMora.getId());
        if (newMora.getCantidad() != mora.getCantidad()) {
            mora.setCantidad(newMora.getCantidad());
        }
        if (newMora.getPrestamo() != mora.getPrestamo()) {
            mora.setPrestamo(newMora.getPrestamo());
        }
        if (newMora.getEstado() != mora.getEstado()) {
            mora.setEstado(newMora.getEstado());
        }
        if (newMora.getFechamora() != mora.getFechamora()) {
            mora.setFechamora(newMora.getFechamora());
        }
        return (Mora) repository.save(mora);
    }

    // Métodos DELETE
    public String deleteMora(int id) {
        repository.deleteById((long) id);
        return "Mora eliminada correctamente";
    }

}
