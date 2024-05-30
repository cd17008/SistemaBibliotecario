package com.ues.fia.bad115.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ues.fia.bad115.clase.Mora;
import com.ues.fia.bad115.clase.Prestamo;
import com.ues.fia.bad115.service.MoraService;

import java.util.List;

@RestController
public class MoraController {
    @Autowired
    private MoraService service;

    @PostMapping("/addmora")
    public Mora addMora(@RequestBody Mora mora) {
        return service.saveMora(mora);
    }

    @GetMapping("/mora")
    public List<Mora> findAllMoras() {
        return service.getMoras();
    }

    @GetMapping("/mora/{id}")
    public Mora findMoraById(@PathVariable int id) {
        return service.getMora(id);
    }

    @GetMapping("/mora/cantidad/{cantidad}")
    public Mora findMorasByCantidad(@PathVariable float cantidad) {
        return service.findMorasByCantidad(cantidad);
    }

    @GetMapping("/mora/idprestamo/{idprestamo}")
    public Mora findMorasByIdprestamo(@PathVariable int idprestamo) {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(idprestamo);
        return service.findMorasByPrestamo(prestamo);
    }

    @GetMapping("/mora/estado/{estado}")
    public Mora findMorasByEstado(@PathVariable int estado) {
        return service.findMorasByEstado(estado);
    }

    @GetMapping("/mora/fechamora/{fechamora}")
    public Mora findMorasByFechamora(@PathVariable java.sql.Date fechamora) {
        return service.findMorasByFechamora(fechamora);
    }

    @GetMapping("/mora/cantidadup/{cantidad}")
    public List<Mora> findMorasUp(@PathVariable float cantidad) {
        return service.findMorasUp(cantidad);
    }

    @GetMapping("/mora/cantidaddown/{cantidad}")
    public List<Mora> findMorasDown(@PathVariable float cantidad) {
        return service.findMorasDown(cantidad);
    }

    @DeleteMapping("/delmora/{id}")
    public String deleteMora(@PathVariable int id) {
        return service.deleteMora(id);
    }

    @PutMapping("/updatemora")
    public Mora updateMora(@RequestBody Mora mora) {
        return service.updateMora(mora);
    }

}
