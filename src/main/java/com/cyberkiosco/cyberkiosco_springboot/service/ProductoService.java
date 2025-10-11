
package com.cyberkiosco.cyberkiosco_springboot.service;

import com.cyberkiosco.cyberkiosco_springboot.entity.Producto;
import com.cyberkiosco.cyberkiosco_springboot.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }
    
    public Producto encontrarPorId(long id) {
        return productoRepository.findById(id).orElse(null);
    }
    
    public boolean existePorId(long id) {
        return productoRepository.existsById(id);
    }
    
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto); //guarda y actualiza si ya existe
        // si se elimina un producto y se ingresa uno con la misma id da error !!!
    }
    
    public void eliminarProductoPorId(long id) {
        productoRepository.deleteById(id);
    }
    
    public long contarProductos() {
        return productoRepository.count();
    }
    
    public void guardarListaProductos(List<Producto> listaProductos) {
        productoRepository.saveAll(listaProductos);
    }
    
    
}
