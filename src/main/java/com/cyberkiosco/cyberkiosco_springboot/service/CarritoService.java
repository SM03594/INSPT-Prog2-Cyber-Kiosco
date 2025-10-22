
package com.cyberkiosco.cyberkiosco_springboot.service;

import com.cyberkiosco.cyberkiosco_springboot.entity.Carrito;
import com.cyberkiosco.cyberkiosco_springboot.entity.Usuario;
import com.cyberkiosco.cyberkiosco_springboot.repository.CarritoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarritoService {
    
    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    
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
    
    public List<Carrito> encontrarPorId_usuario(long id_usuario) {
        Usuario usr = usuarioService.encontrarPorId(id_usuario);
        
        if(usr == null) {
            throw new IllegalArgumentException("El usuario cuyos carritos hay que buscar no existe.");
        }
        
        return encontrarPorUsuario(usr);
    }
    
    public List<Carrito> encontrarPorUsuario(Usuario usr) {
        List<Carrito> listaCarritos = carritoRepository.findByUsuario(usr);

        return listaCarritos;
    }
    
}
