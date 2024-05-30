package com.ues.fia.bad115.controller;

import com.ues.fia.bad115.clase.Autor;
import com.ues.fia.bad115.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutorController {
    @Autowired
    private AutorService service;

    @PostMapping("/addautor")
    public Autor addAutor(@RequestBody Autor autor) {
        return service.saveAutor(autor);
    }

    @GetMapping("/autor")
    public List<Autor> findAllAutores() {
        return service.getAutores();
    }

    @GetMapping("/autor/{id}")
    public Autor findAutorById(@PathVariable int id) {
        return service.getAutor(id);
    }

    @GetMapping("/autor/pais/{filtro}")
    public List<Autor> findAutoresByCountry(@PathVariable String filtro) {
        return service.findAutoresByCountry(filtro);
    }

    @DeleteMapping("/delautor/{id}")
    public String deleteAutor(@PathVariable int id) {
        return service.deleteAutor(id);
    }

    @PutMapping("/updateautor")
    public Autor updateAutor(@RequestBody Autor autor) {
        return service.updateAutor(autor);
    }
}
