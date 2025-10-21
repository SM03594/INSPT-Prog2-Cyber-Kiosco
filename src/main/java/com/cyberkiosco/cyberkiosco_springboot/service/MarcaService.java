package com.cyberkiosco.cyberkiosco_springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberkiosco.cyberkiosco_springboot.entity.Marca;
import com.cyberkiosco.cyberkiosco_springboot.repository.MarcaRepository;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public void guardarMarca(Marca marca){
        this.marcaRepository.save(marca);
    }

    public Marca findById(long id){
        return this.marcaRepository.findById(id).orElse(null);
    }

    public boolean existePorId(long id){
        return this.marcaRepository.existsById(id);
    }

    public void deleteById(long id){
        this.marcaRepository.deleteById(id);
    }

    public List<Marca> findAll(){
        return this.marcaRepository.findAll();
    }

    
}
