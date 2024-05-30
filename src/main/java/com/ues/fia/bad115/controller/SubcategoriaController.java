package com.ues.fia.bad115.controller;

import org.springframework.web.bind.annotation.*;
import com.ues.fia.bad115.clase.Subcategoria;
import com.ues.fia.bad115.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class SubcategoriaController {
    @Autowired
    private SubcategoriaService service;

    @PostMapping("/addsubcategoria")
    public Subcategoria addSubcategoria(@RequestBody Subcategoria subcategoria) {
        return service.saveSubcategoria(subcategoria);
    }

    @GetMapping("/subcategoria")
    public List<Subcategoria> findAllSubcategorias() {
        return service.getSubcategorias();
    }

    @GetMapping("/subcategoria/{id}")
    public Subcategoria findSubcategoriaById(@PathVariable int id) {
        return service.getSubcategoria(id);
    }

    @DeleteMapping("/delsubcategoria/{id}")
    public String deleteSubcategoria(@PathVariable int id) {
        return service.deleteSubcategoria(id);
    }

    @PutMapping("/updatesubcategoria")
    public Subcategoria updateSubcategoria(@RequestBody Subcategoria subcategoria) {
        return service.updateSubcategoria(subcategoria);
    }
}
