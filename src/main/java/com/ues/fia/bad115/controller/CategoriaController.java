package com.ues.fia.bad115.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ues.fia.bad115.clase.Categoria;
import com.ues.fia.bad115.service.CategoriaService;

import java.util.List;

@RestController
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @PostMapping("/addcategoria")
    public Categoria addCategoria(@RequestBody Categoria categoria) {
        return service.saveCategoria(categoria);
    }

    @GetMapping("/categoria")
    public List<Categoria> findAllCategorias() {
        return service.getCategorias();
    }

    @GetMapping("/categoria/{id}")
    public Categoria findCategoriaById(@PathVariable int id) {
        return service.getCategoria(id);
    }

    @DeleteMapping("/delcategoria/{id}")
    public String deleteCategoria(@PathVariable int id) {
        return service.deleteCategoria(id);
    }

    @PutMapping("/updatecategoria")
    public Categoria updateCategoria(@RequestBody Categoria categoria) {
        return service.updateCategoria(categoria);
    }
}
