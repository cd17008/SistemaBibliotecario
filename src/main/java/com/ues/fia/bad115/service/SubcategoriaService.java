package com.ues.fia.bad115.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ues.fia.bad115.clase.Subcategoria;
import com.ues.fia.bad115.repository.SubcategoriaRepository;

import java.util.List;

@Service
public class SubcategoriaService {
    @Autowired
    private SubcategoriaRepository repository;

    // Métodos GET
    public List<Subcategoria> getSubcategorias() {
        return repository.findAll();
    }

    public List<Subcategoria> findSubcategorias(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.search(filtro);
        }
    }

    public Subcategoria getSubcategoria(int id) {
        return (Subcategoria) repository.findById((long) id).orElse(null);  
    }

    // Métodos POST
    public Subcategoria saveSubcategoria(Subcategoria subcategoria) {
        return (Subcategoria) repository.save(subcategoria);
    }

    public List<Subcategoria> saveSubcategorias(List<Subcategoria> subcategorias) {
        return repository.saveAll(subcategorias);
    }

    // Métodos UPDATE
    public Subcategoria updateSubcategoria(Subcategoria newSubcategoria) {
        Subcategoria subcategoria = (Subcategoria) repository.findById((long) newSubcategoria.getId()).orElse(null);
        if (newSubcategoria.getNombre() != null) {
            subcategoria.setNombre(newSubcategoria.getNombre());
        }
        return (Subcategoria) repository.save(subcategoria);
    }

    // Métodos DELETE
    public String deleteSubcategoria(int id) {
        repository.deleteById((long) id);
        return "Subcategoria eliminada correctamente";
    }
}
