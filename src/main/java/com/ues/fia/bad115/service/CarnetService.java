package com.ues.fia.bad115.service;

import com.ues.fia.bad115.clase.Carnet;
import com.ues.fia.bad115.repository.CarnetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Carnet getCarnet(String id){
        return carnetRepository.findById(id);
    }

    public List<Carnet> getCarnetByEstado(int estado){
        return carnetRepository.findByEstado(estado);
    }

    public List<Carnet> getCarnetByFechaCreacion(Date fechaInicio, Date fechaFin){
        return carnetRepository.findByFechaCreacion(fechaInicio, fechaFin);
    }


    //Métodos POST

    public Carnet saveCarnet(Carnet carnet){
        return carnetRepository.save(carnet);
    }

    public List<Carnet> saveCarnets(List<Carnet> carnet){
        return carnetRepository.saveAll(carnet);
    }

    //Métodos UPDATE

    public Carnet updateCarnet(Carnet newCarnet){
        Carnet carnet = carnetRepository.findById(newCarnet.getId());
        if(newCarnet.getCreacion()!=carnet.getCreacion()){
            carnet.setCreacion(newCarnet.getCreacion());
        }
        if(newCarnet.getEstado()!=carnet.getEstado()){
            carnet.setEstado(newCarnet.getEstado());
        }
        return carnetRepository.save(carnet);
    }

    //Métodos DELETE

    public String deleteCarnet(int id){
        carnetRepository.deleteById((long) id);
        return "Carnet eliminado correctamente";
    }
}
