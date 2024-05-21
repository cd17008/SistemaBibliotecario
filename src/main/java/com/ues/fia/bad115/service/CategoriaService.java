package com.ues.fia.bad115.service;

import com.ues.fia.bad115.clase.Categoria;
import com.ues.fia.bad115.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    //Métodos GET
    public List<Categoria> getCategorias(){
        return categoriaRepository.findAll();
    }

    public List<Categoria> findCategoria(String filtro){
        if(filtro==null||filtro.isEmpty()){
            return categoriaRepository.findAll();
        }else{
            return categoriaRepository.findAll();
        }
    }
}
