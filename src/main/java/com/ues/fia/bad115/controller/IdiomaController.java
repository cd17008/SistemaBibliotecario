package com.ues.fia.bad115.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.ues.fia.bad115.service.IdiomaService;
import com.ues.fia.bad115.clase.Idioma;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IdiomaController {
    @Autowired
    private IdiomaService service;

    @PostMapping("/addidioma")
    public Idioma addIdioma(@RequestBody Idioma idioma) {
        return service.saveIdioma(idioma);
    }

    @GetMapping("/idioma")
    public List<Idioma> findAllIdiomas() {
        return service.getIdiomas();
    }

    @GetMapping("/idioma/{id}")
    public Idioma findIdiomaById(@PathVariable int id) {
        return service.getIdioma(id);
    }

    @DeleteMapping("/delidioma/{id}")
    public String deleteIdioma(@PathVariable int id) {
        return service.deleteIdioma(id);
    }

    @PutMapping("/updateidioma")
    public Idioma updateIdioma(@RequestBody Idioma idioma) {
        return service.updateIdioma(idioma);
    }
}
