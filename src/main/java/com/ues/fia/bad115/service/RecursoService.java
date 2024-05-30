package com.ues.fia.bad115.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ues.fia.bad115.clase.Recurso;
import com.ues.fia.bad115.repository.RecursoRepository;

import java.util.List;

@Service
public class RecursoService {
    @Autowired
    private RecursoRepository repository;

    // Métodos GET
    public List<Recurso> getRecursos() {
        return repository.findAll();
    }

    public List<Recurso> findRecursos(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.search(filtro);
        }
    }

    public Recurso getRecurso(String id) {
        return (Recurso) repository.findById(id).orElse(null);
    }

    // Métodos POST
    public Recurso saveRecurso(Recurso recurso) {
        return (Recurso) repository.save(recurso);
    }

    public List<Recurso> saveRecursos(List<Recurso> recursos) {
        return repository.saveAll(recursos);
    }

    // Métodos UPDATE

    public Recurso updateRecurso(Recurso newRecurso) {
        Recurso recurso = (Recurso) repository.findById(newRecurso.getId()).orElse(null);
        if (newRecurso.getTitulo() != null) {
            recurso.setTitulo(newRecurso.getTitulo());
        }
        if (newRecurso.getAutor() != 0) {
            recurso.setAutor(newRecurso.getAutor());
        }
        if (newRecurso.getSubcategoria() != 0) {
            recurso.setSubcategoria(newRecurso.getSubcategoria());
        }
        if (newRecurso.getDescripcion() != null) {
            recurso.setDescripcion(newRecurso.getDescripcion());
        }
        if (newRecurso.getIdioma() != recurso.getIdioma()) {
            recurso.setIdioma(newRecurso.getIdioma());
        }
        if (newRecurso.getCover() != null) {
            recurso.setCover(newRecurso.getCover());
        }
        if (newRecurso.getCantidad() != recurso.getCantidad()) {
            recurso.setCantidad(newRecurso.getCantidad());
        }
        return (Recurso) repository.save(recurso);
    }

    // Métodos DELETE
    public String deleteRecurso(String id) {
        repository.deleteById(id);
        return "Recurso eliminado con ID:" + id;
    }
}
