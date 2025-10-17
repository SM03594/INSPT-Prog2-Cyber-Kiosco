package com.cyberkiosco.cyberkiosco_springboot.service;

import com.cyberkiosco.cyberkiosco_springboot.entity.CarritoProducto;
import com.cyberkiosco.cyberkiosco_springboot.entity.Producto;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Activa la configuración de H2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarritoProductoServiceTest { 
    
    @Autowired
    CarritoProductoService carritoProductoService;
    
    @Autowired
    ProductoService productoService;
    
    @Autowired
    CarritoService carritoService;
    
    
    @Test
    void testEncontrarPorIdExistente() {
        CarritoProducto resultado = carritoProductoService.encontrarPorId(1L, 1L);
        
        assertNotNull(resultado);
    }
    
    
    @Test
    void testEncontrarPorIdNoExistente() {
        CarritoProducto resultado = carritoProductoService.encontrarPorId(1457L, 1874L);
        
        assertNull(resultado);
    }
    
    
    @Test
    void testExistePorId() {
        Boolean resultado = carritoProductoService.existePorId(2L, 2L);
        
        assertTrue(resultado);
    }
    
    
    @Test
    void testNoExistePorId() {
        Boolean resultado = carritoProductoService.existePorId(8521L, 7412L);
        
        assertFalse(resultado);
    }
    
    
    @Test 
    void testEliminarPorId() {
        Long id_carrito, id_producto;
        
        id_carrito = 1L;
        id_producto = 5L;
        
        assertTrue(carritoProductoService.existePorId(id_carrito, id_producto));
        
        carritoProductoService.eliminarPorId(id_carrito, id_producto);
        
        assertFalse(carritoProductoService.existePorId(id_carrito, id_producto));
    }
    
    
    @Test
    void testGuardar() {
        Long id_carrito, id_producto;
        int cantidad;
        double precio_producto;
        
        id_carrito = 5L;
        id_producto = 1L;
        cantidad = 4;
        precio_producto = 7.25;
        
        assertFalse(carritoProductoService.existePorId(id_carrito, id_producto));
        
        carritoProductoService.guardar(id_carrito, id_producto, cantidad, precio_producto);
        
        assertTrue(carritoProductoService.existePorId(id_carrito, id_producto));
    }
    
}