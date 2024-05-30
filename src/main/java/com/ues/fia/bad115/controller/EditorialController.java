package com.ues.fia.bad115.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ues.fia.bad115.service.EditorialService;
import com.ues.fia.bad115.clase.Editorial;

import java.util.List;

@RestController
public class EditorialController {
    @Autowired
    private EditorialService service;

    @PostMapping("/addeditorial")
    public Editorial addEditorial(@RequestBody Editorial editorial) {
        return service.saveEditorial(editorial);
    }

    @GetMapping("/editorial")
    public List<Editorial> findAllEditoriales() {
        return service.getEditoriales();
    }

    @GetMapping("/editorial/{id}")
    public Editorial findEditorialById(@PathVariable int id) {
        return service.getEditorial(id);
    }

    @DeleteMapping("/deleditorial/{id}")
    public String deleteEditorial(@PathVariable int id) {
        return service.deleteEditorial(id);
    }

    @PutMapping("/updateeditorial")
    public Editorial updateEditorial(@RequestBody Editorial editorial) {
        return service.updateEditorial(editorial);
    }
}
