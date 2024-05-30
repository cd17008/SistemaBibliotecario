package com.ues.fia.bad115.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ues.fia.bad115.clase.Recurso;
import com.ues.fia.bad115.service.RecursoService;

import java.util.List;

@RestController
public class RecursoController {
    @Autowired
    private RecursoService service;

    @PostMapping("/addrecurso")
    public Recurso addRecurso(@RequestBody Recurso recurso) {
        return service.saveRecurso(recurso);
    }

    @GetMapping("/recurso")
    public List<Recurso> findAllRecursos() {
        return service.getRecursos();
    }

    @GetMapping("/recurso/{id}")
    public Recurso findRecursoById(@PathVariable String id) {
        return service.getRecurso(id);
    }

    @DeleteMapping("/delrecurso/{id}")
    public String deleteRecurso(@PathVariable String id) {
        return service.deleteRecurso(id);
    }

    @PutMapping("/updaterecurso")
    public Recurso updateRecurso(@RequestBody Recurso recurso) {
        return service.updateRecurso(recurso);
    }
}
