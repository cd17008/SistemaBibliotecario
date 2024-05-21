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

    //Métodos GET
    public List<Idioma> getIdiomas(){
        return idiomaRepository.findAll();
    }
}
