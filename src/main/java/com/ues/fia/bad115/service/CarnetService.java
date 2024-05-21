package com.ues.fia.bad115.service;

import com.ues.fia.bad115.clase.Carnet;
import com.ues.fia.bad115.repository.CarnetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarnetService {
    @Autowired
    private CarnetRepository carnetRepository;

    //Métodos GET
    public List<Carnet> getCarnets(){
        return carnetRepository.findAll();
    }

    public List<Carnet> findCarnet(String filtro){
        if(filtro==null||filtro.isEmpty()){
            return carnetRepository.findAll();
        }else{
            return carnetRepository.findAll();
        }
    }
    //Métodos POST

    //Métodos UPDATE

    //Métodos DELETE
}
