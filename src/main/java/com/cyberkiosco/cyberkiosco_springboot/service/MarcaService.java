package com.cyberkiosco.cyberkiosco_springboot.service;

import com.cyberkiosco.cyberkiosco_springboot.entity.Marca;
import com.cyberkiosco.cyberkiosco_springboot.repository.MarcaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {
    
    @Autowired
    private MarcaRepository marcaRepository;
    
    
    public List<Marca> obtenerTodos() {
        return marcaRepository.findAll();
    }
    
    public Marca encontrarPorId(long id) {
        return marcaRepository.findById(id).orElse(null);
    }
    
    public boolean existePorId(long id) {
        return marcaRepository.existsById(id);
    }
    
    public void guardar(Marca marca) {
        marcaRepository.save(marca); //guarda y actualiza si ya existe
    }
    
    public void eliminarPorId(long id) {
        marcaRepository.deleteById(id);
    }
    
    public long contar() {
        return marcaRepository.count();
    }
       
}

