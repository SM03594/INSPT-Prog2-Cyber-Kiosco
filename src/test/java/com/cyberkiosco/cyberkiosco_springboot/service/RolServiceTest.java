
package com.cyberkiosco.cyberkiosco_springboot.service;

import com.cyberkiosco.cyberkiosco_springboot.entity.Rol;
import java.util.ArrayList;
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
public class RolServiceTest {
    
    @Autowired
    private RolService rolService;
    
    
    private Rol crearRolEjemplo() {
        Rol rol = new Rol(null, "Empleado");
        
        return rol;
    }
    

    @Test
    @Order(1) //porque luego si se agregan o eliminan registros con los otros tests falla
    void testObtenerTodosLosRoles() {
        ArrayList<Rol> listaRoles;
        listaRoles = (ArrayList<Rol>) this.rolService.obtenerTodos();
        
        for(Rol rol : listaRoles) {
            System.out.println(rol.toString());
        }
        
        assertEquals(2, listaRoles.size()); //en principio son 2 roles en total
    }
    
    @Test
    @Order(2) //porque luego si se agregan o eliminan registros con los otros tests falla
    void testContarRoles() {
        Long total = rolService.contar(); 
        
        assertEquals(2L, total);
    }
    
    @Test
    void testEncontrarPorIdExistente() {
        Rol resultado = rolService.encontrarPorId(1L);
        
        assertNotNull(resultado);
    }
    
    
    @Test
    void testEncontrarPorIdNoExistente() {
        Rol resultado = rolService.encontrarPorId(55L);
        
        assertNull(resultado);
    }
    
    
    @Test
    void testExistePorId() {
        Boolean resultado = rolService.existePorId(2L);
        
        assertTrue(resultado);
    }
    
    
    @Test
    void testNoExistePorId() {
        Boolean resultado = rolService.existePorId(9999L);
        
        assertFalse(resultado);
    }
    
    
    @Test
    @Order(3) //porque luego si se agregan o eliminan registros con los otros tests falla
    void testGuardarRol() {
        Rol rolNuevo = this.crearRolEjemplo();

        rolService.guardar(rolNuevo);
        
        assertTrue(rolService.existePorId(rolNuevo.getId()));
        assertEquals(rolNuevo, rolService.encontrarPorId(rolNuevo.getId()));
    }
    
    
    @Test
    @Order(4)
    void testEliminarCarritoPorId() {
        rolService.eliminarPorId(3L);
        
        assertFalse(rolService.existePorId(3L));
    }

    
}
