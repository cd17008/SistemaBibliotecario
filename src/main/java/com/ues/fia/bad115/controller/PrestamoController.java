package com.ues.fia.bad115.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ues.fia.bad115.clase.Prestamo;
import com.ues.fia.bad115.service.PrestamoService;

import java.util.List;

@RestController
public class PrestamoController {
    @Autowired
    private PrestamoService service;

    @PostMapping("/addprestamo")
    public Prestamo addPrestamo(@RequestBody Prestamo prestamo) {
        return service.savePrestamo(prestamo);
    }

    @GetMapping("/prestamo")
    public List<Prestamo> findAllPrestamos() {
        return service.getPrestamos();
    }

    @GetMapping("/prestamo/{id}")
    public Prestamo findPrestamoById(@PathVariable int id) {
        return service.getPrestamo(id);
    }

    @GetMapping("/prestamobyusuario/{id}")
    public List<Prestamo> findPrestamoByUsuario(@PathVariable int id) {
        return service.getPrestamosByUsuario(id);
    }

    @DeleteMapping("/delprestamo/{id}")
    public String deletePrestamo(@PathVariable int id) {
        return service.deletePrestamo(id);
    }

    @PutMapping("/updateprestamo")
    public Prestamo updatePrestamo(@RequestBody Prestamo prestamo) {
        return service.updatePrestamo(prestamo);
    }

}
