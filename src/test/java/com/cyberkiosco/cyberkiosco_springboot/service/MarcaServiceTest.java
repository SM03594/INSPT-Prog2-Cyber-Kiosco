package com.cyberkiosco.cyberkiosco_springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.cyberkiosco.cyberkiosco_springboot.entity.Marca;

@SpringBootTest
@ActiveProfiles("test")  // Activa la configuración de H2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class MarcaServiceTest {
    @Autowired
    private MarcaService marcaService;

    // En MarcaServiceTest.java
    private Marca crearMarcaEjemplo(String nombre) {
        // El constructor @AllArgsConstructor de Marca requiere:
        // 1. Long id (null para que JPA lo genere)
        // 2. String nombre
        // 3. List<Producto> productos (vacía para un objeto nuevo)
        
        // Usamos List.of() o new ArrayList<>() para el tercer argumento.
        return new Marca(null, nombre, List.of()); 
    }

    @Test
    @Transactional
    void testGuardarMarcaExitosa() {
        // 1. Arrange: Crear la nueva Marca
        Marca nuevaMarca = crearMarcaEjemplo("Nueva Marca Test");

        // 2. Act: Llamar al método guardar
        marcaService.guardarMarca(nuevaMarca);

        // 3. Assert: Verificar la persistencia
        
        // Debe tener un ID asignado por la BD
        assertNotNull(nuevaMarca.getId(), "El ID de la marca debe ser asignado por la base de datos (no debe ser null)."); 
        
        // Debe existir en la base de datos
        assertTrue(marcaService.existePorId(nuevaMarca.getId()), "La marca debe existir en la BD después de guardarse.");
        
        // Opcional: Verificar que el nombre sea correcto
        Marca marcaGuardada = marcaService.findById(nuevaMarca.getId());
        assertEquals("Nueva Marca Test", marcaGuardada.getNombre());
    }

    @Test
    @Transactional
    void testEliminarMarcaPorId(){
        this.marcaService.deleteById(1L);
        assertFalse(this.marcaService.existePorId(1L));
    }

    @Test
    @Transactional
    void testObtenerTodasLasMarcas(){
        ArrayList<Marca> marcas;

        marcas = (ArrayList<Marca>) this.marcaService.findAll();

        for (Marca marca : marcas) {
            System.out.println(marca.toString());
        }

        assertEquals(4, marcas.size());
    }
}
