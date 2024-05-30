package com.ues.fia.bad115.controller;

import com.ues.fia.bad115.service.TransaccionService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.ues.fia.bad115.clase.Transaccion;

import java.util.Date;
import java.util.List;

@RestController
public class TransaccionController {
    @Autowired
    private TransaccionService service;

    @PostMapping("/addtransaccion")
    public Transaccion addTransaccion(@RequestBody Transaccion transaccion) {
        return service.saveTransaccion(transaccion);
    }

    @GetMapping("/transaccion")
    public List<Transaccion> findAllTransacciones() {
        return service.getTransacciones();
    }

    @GetMapping("/transaccion/{id}")
    public Transaccion findTransaccionById(@PathVariable int id) {
        return service.getTransaccionId(id);
    }

    @GetMapping("/transaccion/tipo/{tipo}")
    public List<Transaccion> findTransaccionByTipo(@PathVariable String tipo) {
        return service.getTransaccionTipo(tipo);
    }

    @GetMapping("/transaccion/fecha/{fecha}")
    public List<Transaccion> findTransaccionByFecha(@PathVariable Date fecha) {
        return service.getTransaccionFecha(fecha);
    }

    @GetMapping("/transaccion/monto/{monto}")
    public List<Transaccion> findTransaccionByMonto(@PathVariable float monto) {
        return service.getTransaccionMonto(monto);
    }

    @GetMapping("/transaccion/idusuario/{idusuario}")
    public List<Transaccion> findTransaccionByIdUsuario(@PathVariable int idusuario) {
        return service.getTransaccionIdUsuario(idusuario);
    }

    @GetMapping("/transaccion/monto/mayor/{monto}")
    public List<Transaccion> findTransaccionByMontoMayor(@PathVariable float monto) {
        return service.getTransaccionMontoMayor(monto);
    }

    @GetMapping("/transaccion/monto/menor/{monto}")
    public List<Transaccion> findTransaccionByMontoMenor(@PathVariable float monto) {
        return service.getTransaccionMontoMenor(monto);
    }

    @GetMapping("/transaccion/fecha/mayor/{fecha}")
    public List<Transaccion> findTransaccionByFechaMayor(@PathVariable Date fecha) {
        return service.getTransaccionFechaMayor(fecha);
    }

    @GetMapping("/transaccion/fecha/menor/{fecha}")
    public List<Transaccion> findTransaccionByFechaMenor(@PathVariable Date fecha) {
        return service.getTransaccionFechaMenor(fecha);
    }

    @DeleteMapping("/deltransaccion/{id}")
    public String deleteTransaccion(@PathVariable int id) {
        return service.deleteTransaccion(id);
    }

    @PutMapping("/updatetransaccion")
    public Transaccion updateTransaccion(@RequestBody Transaccion transaccion) {
        return service.updateTransaccion(transaccion);
    }

}
