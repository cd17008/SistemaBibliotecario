package com.ues.fia.bad115.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.ues.fia.bad115.clase.Carnet;
import com.ues.fia.bad115.service.CarnetService;

import java.util.Date;
import java.util.List;

@RestController
public class CarnetController {
    @Autowired
    private CarnetService service;

    @PostMapping("/addcarnet")
    public Carnet addCarnet(@RequestBody Carnet carnet) {
        return service.saveCarnet(carnet);
    }

    @GetMapping("/carnet")
    public List<Carnet> findAllCarnets() {
        return service.getCarnets();
    }

    @GetMapping("/carnet/{id}")
    public Carnet findCarnetById(@PathVariable String id) {
        return service.getCarnet(id);
    }

    @GetMapping("/carnet/estado/{filtro}")
    public List<Carnet> findCarnetByEstado(@PathVariable int filtro) {
        return service.getCarnetByEstado(filtro);
    }

    @GetMapping("/carnet/fecha/{fechaInicio}/{fechaFin}")
    public List<Carnet> findCarnetByFechaCreacion(@PathVariable Date fechaInicio, @PathVariable Date fechaFin) {
        return service.getCarnetByFechaCreacion(fechaInicio, fechaFin);
    }

    @DeleteMapping("/delcarnet/{id}")
    public String deleteCarnet(@PathVariable int id) {
        return service.deleteCarnet(id);
    }

    @PutMapping("/updatecarnet")
    public Carnet updateCarnet(@RequestBody Carnet carnet) {
        return service.updateCarnet(carnet);
    }

}
