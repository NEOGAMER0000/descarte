/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Modelo.Motorbikes;
import com.example.demo.Repositorio.MotorbikesRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service

public class MotorbikesServicio {
       @Autowired
    private MotorbikesRepositorio motorbikesRepositorio;

    public List<Motorbikes> getAll(){
        return motorbikesRepositorio.getAll();
    }

    public Optional<Motorbikes> getMotorbikes(int motorbikesId) {
        return motorbikesRepositorio.getMotorbikes(motorbikesId);
    }

    public Motorbikes save(Motorbikes motorbikes){
        if(motorbikes.getId()==null){
            return motorbikesRepositorio.save(motorbikes);
        }else{
            Optional<Motorbikes>e= motorbikesRepositorio.getMotorbikes(motorbikes.getId());
            if(e.isEmpty()){
                return motorbikesRepositorio.save(motorbikes);
            }else{
                return motorbikes;
            }
        }
    }
    
       public Motorbikes update(Motorbikes motorbikes){
        if(motorbikes.getId()!=null){
            Optional<Motorbikes> e= motorbikesRepositorio.getMotorbikes(motorbikes.getId());
            if(!e.isEmpty()){
                if(motorbikes.getName()!=null){
                    e.get().setName(motorbikes.getName());
                }
                if(motorbikes.getBrand()!=null){
                    e.get().setBrand(motorbikes.getBrand());
                }
                if(motorbikes.getYear()!=null){
                    e.get().setYear(motorbikes.getYear());
                }
                if(motorbikes.getDescription()!=null){
                    e.get().setDescription(motorbikes.getDescription());
                }
                if(motorbikes.getCategory()!=null){
                    e.get().setCategory(motorbikes.getCategory());
                }
                motorbikesRepositorio.save(e.get());
                return e.get();
            }else{
                return motorbikes;
            }
        }else{
            return motorbikes;
        }
    }

    
    
      public boolean deleteMotorbikes (int id){
        Boolean d = getMotorbikes(id).map(motorbikes -> {
            motorbikesRepositorio.delete(motorbikes);
            return true;
        }).orElse(false);
        return d;
    }
    
    
}
