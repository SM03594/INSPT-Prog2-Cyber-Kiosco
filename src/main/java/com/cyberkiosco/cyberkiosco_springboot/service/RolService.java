
package com.cyberkiosco.cyberkiosco_springboot.service;

import com.cyberkiosco.cyberkiosco_springboot.entity.Rol;
import com.cyberkiosco.cyberkiosco_springboot.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RolService {
    
    @Autowired
    private RolRepository rolRepository;
    
    
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }
    
    public Rol encontrarPorId(long id) {
        return rolRepository.findById(id).orElse(null);
    }
    
    public boolean existePorId(long id) {
        return rolRepository.existsById(id);
    }
    
    public void guardar(Rol rol) {
        rolRepository.save(rol); //guarda y actualiza si ya existe
    }
    
    public void eliminarPorId(long id) {
        rolRepository.deleteById(id);
    }
    
    public long contar() {
        return rolRepository.count();
    }
    
}
