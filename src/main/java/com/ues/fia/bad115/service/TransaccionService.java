package com.ues.fia.bad115.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ues.fia.bad115.repository.TransaccionRepository;
import com.ues.fia.bad115.clase.Transaccion;
import com.ues.fia.bad115.clase.Usuario;

import java.util.Date;
import java.util.List;

@Service
public class TransaccionService {
    @Autowired
    private TransaccionRepository repository;

    // Métodos GET
    public List<Transaccion> getTransacciones() {
        return repository.findAll();
    }

    public Transaccion getTransaccionId(int id) {
        return (Transaccion) repository.findById((long) id).orElse(null);
    }

    public List<Transaccion> getTransaccionTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    public List<Transaccion> getTransaccionFecha(Date fecha) {
        return repository.findByFecha(fecha);
    }

    public List<Transaccion> getTransaccionMonto(float monto) {
        return repository.findByMonto(monto);
    }

    public List<Transaccion> getTransaccionIdUsuario(int idusuario) {
        Usuario usuario = new Usuario();
        usuario.setId(idusuario);
        return repository.findByUsuario(usuario);
    }

    public List<Transaccion> getTransaccionMontoMayor(float monto) {
        return repository.searchByMontoMayor(monto);
    }

    public List<Transaccion> getTransaccionMontoMenor(float monto) {
        return repository.searchByMontoMenor(monto);
    }

    public List<Transaccion> getTransaccionFechaMayor(Date fecha) {
        return repository.searchByFechaMayor(fecha);
    }

    public List<Transaccion> getTransaccionFechaMenor(Date fecha) {
        return repository.searchByFechaMenor(fecha);
    }

    // Métodos POST
    public Transaccion saveTransaccion(Transaccion transaccion) {
        return (Transaccion) repository.save(transaccion);
    }

    public List<Transaccion> saveTransacciones(List<Transaccion> transacciones) {
        return repository.saveAll(transacciones);
    }

    // Métodos UPDATE
    public Transaccion updateTransaccion(Transaccion newTransaccion) {
        Transaccion transaccion = (Transaccion) repository.findById(newTransaccion.getId());
        if (newTransaccion.getFecha() != transaccion.getFecha()) {
            transaccion.setFecha(newTransaccion.getFecha());
        }
        if (newTransaccion.getMonto() != transaccion.getMonto()) {
            transaccion.setMonto(newTransaccion.getMonto());
        }
        if (newTransaccion.getTipo() != transaccion.getTipo()) {
            transaccion.setTipo(newTransaccion.getTipo());
        }
        if (newTransaccion.getDescripcion() != transaccion.getDescripcion()) {
            transaccion.setDescripcion(newTransaccion.getDescripcion());
        }
        if (newTransaccion.getIdusuario() != transaccion.getIdusuario()) {
            transaccion.setIdusuario(newTransaccion.getIdusuario());
        }
        return (Transaccion) repository.save(transaccion);
    }

    // Métodos DELETE
    public String deleteTransaccion(int id) {
        repository.deleteById((long) id);
        return "Transaccion eliminada con ID:" + id;
    }

}
