
package com.cyberkiosco.cyberkiosco_springboot.service;

import com.cyberkiosco.cyberkiosco_springboot.entity.Carrito;
import com.cyberkiosco.cyberkiosco_springboot.repository.CarritoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarritoService {
    
    @Autowired
    private CarritoRepository carritoRepository;
    
    
    public List<Carrito> obtenerTodosLosCarritos() {
        return carritoRepository.findAll();
    }
    
    public Carrito encontrarPorId(long id) {
        return carritoRepository.findById(id).orElse(null);
    }
    
    public boolean existePorId(long id) {
        return carritoRepository.existsById(id);
    }
    
    public void guardarCarrito(Carrito carrito) {
        carritoRepository.save(carrito); //guarda y actualiza si ya existe
        // si se elimina un producto y se ingresa uno con la misma id da error !!!
    }
    
    public void eliminarCarritoPorId(long id) {
        carritoRepository.deleteById(id);
    }
    
    public long contarCarritos() {
        return carritoRepository.count();
    }
    
    public void guardarListaCarritos(List<Carrito> listaCarritos) {
        carritoRepository.saveAll(listaCarritos);
    }
    
    
}
