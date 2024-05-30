package com.ues.fia.bad115.service;

import com.ues.fia.bad115.clase.Categoria;
import com.ues.fia.bad115.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    // Métodos GET
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    public List<Categoria> findCategoria(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return categoriaRepository.findAll();
        } else {
            return categoriaRepository.findAll();
        }
    }

    public Categoria getCategoria(int id) {
        return (Categoria) categoriaRepository.findById((long) id).orElse(null);
    }

    // Métodos POST
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> saveCategorias(List<Categoria> categorias) {
        return categoriaRepository.saveAll(categorias);
    }

    // Métodos UPDATE
    public Categoria updateCategoria(Categoria newCategoria) {
        Categoria categoria = (Categoria) categoriaRepository.findById((long) newCategoria.getId()).orElse(null);
        if (newCategoria.getNombre() != null) {
            categoria.setNombre(newCategoria.getNombre());
        }
        if (newCategoria.getDescripcion() != null) {
            categoria.setDescripcion(newCategoria.getDescripcion());
        }
        if (newCategoria.getDisponible() != 0) {
            categoria.setDisponible(newCategoria.getDisponible());
        }
        return categoriaRepository.save(categoria);

    }

    // Métodos DELETE
    public String deleteCategoria(int id) {
        categoriaRepository.deleteById((long) id);
        return "Categoria eliminada correctamente";
    }
}
