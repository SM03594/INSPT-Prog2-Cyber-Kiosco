package com.cyberkiosco.cyberkiosco_springboot.service;

import com.cyberkiosco.cyberkiosco_springboot.entity.Carrito;
import com.cyberkiosco.cyberkiosco_springboot.entity.CarritoProducto;
import com.cyberkiosco.cyberkiosco_springboot.entity.Producto;
import java.util.List;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Activa la configuración de H2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
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
    
    
    @Test
    @Order(1)
    void testObtenerTodosLosProductosEnCarrito() {
        List<Producto> listaProductos = carritoProductoService.obtenerTodosLosProductosEnCarrito(1L);
        List<Producto> productosEsperados = List.of(
                productoService.encontrarPorId(1L),
                productoService.encontrarPorId(3L),
                productoService.encontrarPorId(5L)
        );
        
        assertEquals(3, listaProductos.size()); //hay 3 productos en el carrito con id 1 en principio
        
        System.out.println(
            listaProductos.get(0).toString() + "\n" +
            listaProductos.get(1).toString() + "\n" +
            listaProductos.get(2).toString() + "\n"
        );
        
        assertTrue(listaProductos.containsAll(productosEsperados), "No todos los productos esperados están en el carrito.");
    }
    
    
    @Test
    @Order(2)
    void testObtenerTodosCarritosConProducto() {
        List<Carrito> listaCarritos = carritoProductoService.obtenerTodosLosCarritosConProducto(2L);
        List<Carrito> carritosEsperados = List.of(
                carritoService.encontrarPorId(2L),
                carritoService.encontrarPorId(10L)
        );
                
        assertEquals(2, listaCarritos.size()); //hay 2 carritos con producto con id 1 en principio
        
        System.out.println(
            listaCarritos.get(0).toString() + "\n" +
            listaCarritos.get(1).toString() + "\n"
        );
        
        assertTrue(listaCarritos.containsAll(carritosEsperados), "No todos los carritos esperados estan en la lista.");
    }
    
}