package com.ues.fia.bad115.service;

import com.ues.fia.bad115.clase.Autor;
import com.ues.fia.bad115.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository repository;

    // Métodos GET
    public List<Autor> getAutores() {
        return repository.findAll();
    }

    public List<Autor> findAutores(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.search(filtro);
        }
    }

    public List<Autor> findAutoresByCountry(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.searchByCountry(filtro);
        }
    }

    public Autor getAutor(int id) {
        return (Autor) repository.findById((long) id).orElse(null);
    }

    // Métodos POST
    public Autor saveAutor(Autor autor) {
        return (Autor) repository.save(autor);
    }

    public List<Autor> saveAutores(List<Autor> autores) {
        return repository.saveAll(autores);
    }

    // Métodos UPDATE
    public Autor updateAutor(Autor newAutor) {
        Autor autor = (Autor) repository.findById(newAutor.getId()).orElse(null);
        if (newAutor.getNombre() != null) {
            autor.setNombre(newAutor.getNombre());
        }
        if (newAutor.getApellido() != null) {
            autor.setApellido(newAutor.getApellido());
        }
        if (newAutor.getPseudonimo() != null) {
            autor.setPseudonimo(newAutor.getPseudonimo());
        }
        if (newAutor.getPais() != null) {
            autor.setPais(newAutor.getPais());
        }
        return (Autor) repository.save(autor);
    }

    // Métodos DELETE
    public String deleteAutor(int id) {
        repository.deleteById((long) id);
        return "Autor eliminado con ID:" + id;
    }

}
