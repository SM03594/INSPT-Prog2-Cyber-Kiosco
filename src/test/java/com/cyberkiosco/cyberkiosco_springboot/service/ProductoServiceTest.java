package com.cyberkiosco.cyberkiosco_springboot.service;

import com.cyberkiosco.cyberkiosco_springboot.entity.Producto;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")  // Activa la configuración de H2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductoServiceTest {


    @Autowired
    private ProductoService productoService;


    private Producto crearProductoEjemplo1() {
        return new Producto(
                null,  // hay que poner la id null porque le JPA se encarga de darsela
                "Agua Mineral Saborizada",
                10,
                15.0,
                "img/aguaMineralSaborizada.jpg",
                2,
                2
        );
    }
    
    private Producto crearProductoEjemplo2() {
        return new Producto(
                null,
                "Barra de Fijoles",
                35,
                20.0,
                "img/barraDeFijoles.png",
                1,
                3
        );
    }
    
    private Producto crearProductoEjemplo3() {
        return new Producto(
            null,
            "Galletas Integrales",
            50,
            15.5,
            "img/galletasIntegrales.png",
            1,
            4
        );
    }
    
    // Los tests se hacen en orden aleatorio excepto por el uso de @Order

    @Test
    @Order(1) //porque luego si se agregan o eliminan registros con los otros tests falla
    void testObtenerTodosLosProductos() {
        ArrayList<Producto> listaProductos;
        listaProductos = (ArrayList<Producto>) this.productoService.obtenerTodosLosProductos();
        
        for(Producto producto : listaProductos) {
            System.out.println(producto.toString());
        }
        
        assertEquals(15, listaProductos.size()); //en principio son 15 productos en total
    }

    
    @Test
    void testEncontrarPorIdExistente() {
        Producto resultado = productoService.encontrarPorId(1L);
        
        assertNotNull(resultado);
        assertEquals("Chips Picantes", resultado.getNombre());
    }

    
    @Test
    void testEncontrarPorIdNoExistente() {
        Producto resultado = productoService.encontrarPorId(12345L);
        
        assertNull(resultado);
    }

    
    @Test
    void testExistePorId() {
        Boolean resultado = productoService.existePorId(2L);
        
        assertTrue(resultado);
    }
    
    
    @Test
    void testNoExistePorId() {
        Boolean resultado = productoService.existePorId(8521L);
        
        assertFalse(resultado);
    }

    
    @Test
    void testGuardarProducto() {
        Producto producto = crearProductoEjemplo1(); 

        productoService.guardarProducto(producto); // +1 producto cantidad actual 16
        
        assertTrue(productoService.existePorId(producto.getId()));
        assertEquals("Agua Mineral Saborizada", productoService.encontrarPorId(producto.getId()).getNombre());
    }

    
    @Test
    void testEliminarProductoPorId() {
        productoService.eliminarProductoPorId(1L); // -1 producto cantidad acutal 15
        
        assertFalse(productoService.existePorId(1L));
    }

    
    @Test
    @Order(2) //porque luego si se agregan o eliminan registros con los otros tests falla
    void testContarProductos() {
        Long total = productoService.contarProductos(); 
        
        assertEquals(15L, total);
    }

    
    @Test
    void testGuardarListaProductos() {
        List<Producto> productos = List.of(
                crearProductoEjemplo2(),
                crearProductoEjemplo3()
        );

        productoService.guardarListaProductos(productos);
        
        assertTrue(productoService.existePorId(productos.get(0).getId()));
        assertEquals(productos.get(0).getNombre(), productoService.encontrarPorId(17L).getNombre());
        
        assertTrue(productoService.existePorId(productos.get(0).getId()));
        assertEquals(productos.get(1).getNombre(), productoService.encontrarPorId(18L).getNombre());
        
        Long total = productoService.contarProductos();
        
        //assertEquals(18L, total);
    }
    
}
