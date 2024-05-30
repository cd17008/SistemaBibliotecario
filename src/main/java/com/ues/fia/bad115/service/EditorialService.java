package com.ues.fia.bad115.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ues.fia.bad115.repository.EditorialRepository;
import com.ues.fia.bad115.clase.Editorial;
import java.util.List;

@Service
public class EditorialService {
    @Autowired
    private EditorialRepository repository;

    // Métodos GET
    public List<Editorial> getEditoriales() {
        return repository.findAll();
    }

    public List<Editorial> findEditoriales(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.search(filtro);
        }
    }

    public Editorial getEditorial(int id) {
        return (Editorial) repository.findById((long) id).orElse(null);
    }

    // Métodos POST
    public Editorial saveEditorial(Editorial editorial) {
        return (Editorial) repository.save(editorial);
    }

    public List<Editorial> saveEditoriales(List<Editorial> editoriales) {
        return repository.saveAll(editoriales);
    }

    // Métodos UPDATE
    public Editorial updateEditorial(Editorial newEditorial) {
        Editorial editorial = (Editorial) repository.findById((long) newEditorial.getId()).orElse(null);
        if (newEditorial.getNombre() != null) {
            editorial.setNombre(newEditorial.getNombre());
        }
        return (Editorial) repository.save(editorial);
    }

    // Métodos DELETE
    public String deleteEditorial(int id) {
        repository.deleteById((long) id);
        return "Editorial eliminado correctamente";
    }

}
