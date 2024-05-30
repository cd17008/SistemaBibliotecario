package com.ues.fia.bad115.service;

import com.ues.fia.bad115.clase.Idioma;
import com.ues.fia.bad115.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService {
    @Autowired
    private IdiomaRepository idiomaRepository;

    // Métodos GET
    // Este método retorna una lista de todos los idiomas
    public List<Idioma> getIdiomas() {
        return idiomaRepository.findAll();
    }

    // Este método retorna una lista de idiomas que coincidan con el filtro
    public List<Idioma> findIdioma(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return idiomaRepository.findAll();
        } else {
            return idiomaRepository.search(filtro);
        }
    }

    // Este método retorna un idioma por su id
    public Idioma getIdioma(int id) {
        return (Idioma) idiomaRepository.findById((long) id).orElse(null);
    }

    // Metodos POST
    // Este método guarda un idioma
    public Idioma saveIdioma(Idioma idioma) {
        return (Idioma) idiomaRepository.save(idioma);
    }

    // Este método guarda una lista de idiomas
    public List<Idioma> saveIdiomas(List<Idioma> idiomas) {
        return idiomaRepository.saveAll(idiomas);
    }

    // Metodos UPDATE
    // Este método actualiza un idioma
    public Idioma updateIdioma(Idioma newIdioma) {
        Idioma idioma = (Idioma) idiomaRepository.findById((long) newIdioma.getId()).orElse(null);
        if (newIdioma.getIdioma() != null) {
            idioma.setIdioma(newIdioma.getIdioma());
        }
        return idiomaRepository.save(idioma);
    }

    // Metodos DELETE
    // Este método elimina un idioma
    public String deleteIdioma(int id) {
        idiomaRepository.deleteById((long) id);
        return "Idioma con id:" + id + " eliminado correctamente";
    }

}
