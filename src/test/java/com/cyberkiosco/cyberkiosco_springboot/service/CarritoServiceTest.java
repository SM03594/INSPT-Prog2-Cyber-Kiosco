package com.cyberkiosco.cyberkiosco_springboot.service;

import com.cyberkiosco.cyberkiosco_springboot.entity.Carrito;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")  // Activa la configuración de H2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class CarritoServiceTest {
    
    private Carrito crearCarritoEjemplo1() {
        return new Carrito(
            null,                                
            45.50,                               
            LocalDateTime.of(2025, 10, 1, 14, 30)
        );
    }

    private Carrito crearCarritoEjemplo2() {
        return new Carrito(
            null,
            22.30,
            LocalDateTime.of(2025, 10, 5, 10, 0)
        );
    }

    private Carrito crearCarritoEjemplo3() {
        return new Carrito(
            null,
            89.90,
            LocalDateTime.of(2025, 10, 12, 18, 45)
        );
    }


    @Autowired
    private CarritoService carritoService;
    
    // Los tests se hacen en orden aleatorio excepto por el uso de @Order

    @Test
    @Order(1) //porque luego si se agregan o eliminan registros con los otros tests falla
    void testObtenerTodosLosCarritos() {
        ArrayList<Carrito> listaCarritos;
        listaCarritos = (ArrayList<Carrito>) this.carritoService.obtenerTodosLosCarritos();
        
        for(Carrito carrito : listaCarritos) {
            System.out.println(carrito.toString());
        }
        
        assertEquals(10, listaCarritos.size()); //en principio son 10 carritos en total
    }
    
    @Test
    @Order(2) //porque luego si se agregan o eliminan registros con los otros tests falla
    void testContarCarritos() {
        Long total = carritoService.contarCarritos(); 
        
        assertEquals(10L, total);
    }

    
    @Test
    void testEncontrarPorIdExistente() {
        Carrito resultado = carritoService.encontrarPorId(3L);
        
        assertNotNull(resultado);
    }
    
    
    @Test
    void testEncontrarPorIdNoExistente() {
        Carrito resultado = carritoService.encontrarPorId(9999L);
        
        assertNull(resultado);
    }
    
    
    @Test
    void testExistePorId() {
        Boolean resultado = carritoService.existePorId(2L);
        
        assertTrue(resultado);
    }
    
    
    @Test
    void testNoExistePorId() {
        Boolean resultado = carritoService.existePorId(9999L);
        
        assertFalse(resultado);
    }
    
    
    @Test
    void testGuardarCarrito() {
        Carrito carrito = crearCarritoEjemplo1();

        carritoService.guardarCarrito(carrito);
        
        assertTrue(carritoService.existePorId(carrito.getId()));
        assertTrue(carrito.equals(carritoService.encontrarPorId(carrito.getId())));
    }
    
    
    @Test
    void testEliminarCarritoPorId() {
        carritoService.eliminarCarritoPorId(1L);
        
        assertFalse(carritoService.existePorId(1L));
    }
    
    
    @Test
    void testGuardarListaCarritos() {
        List<Carrito> carritos = List.of(
                crearCarritoEjemplo2(),
                crearCarritoEjemplo3()
        );

        Long totalAntes = carritoService.contarCarritos();
        
        carritoService.guardarListaCarritos(carritos);
        
        assertTrue(carritoService.existePorId(carritos.get(0).getId()));
        assertTrue(carritos.get(0).equals(carritoService.encontrarPorId(carritos.get(0).getId())));
        
        assertTrue(carritoService.existePorId(carritos.get(1).getId()));
        assertTrue(carritos.get(1).equals(carritoService.encontrarPorId(carritos.get(1).getId())));
        
        Long totalDespues = carritoService.contarCarritos();
        
        assertEquals(2L, totalDespues - totalAntes);
    }
    
}
