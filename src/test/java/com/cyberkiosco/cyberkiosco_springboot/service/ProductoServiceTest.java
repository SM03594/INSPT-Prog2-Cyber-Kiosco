package com.cyberkiosco.cyberkiosco_springboot.service;

import com.cyberkiosco.cyberkiosco_springboot.entity.Producto;
import com.cyberkiosco.cyberkiosco_springboot.repository.ProductoRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;


    private Producto crearProductoEjemplo1() {
        return new Producto(
                1L,
                "agua mineral",
                10,
                15.0,
                "img/aguaMineral.jpg",
                2,
                5
        );
    }
    
    private Producto crearProductoEjemplo2() {
        return new Producto(
                2L,
                "barrita de chocolate",
                35,
                20.0,
                "img/barraChocolate.jpg",
                1,
                3
        );
    }
    

    @Test
    void testObtenerTodosLosProductos() {
        List<Producto> mockProductos = List.of(
                crearProductoEjemplo1(),
                crearProductoEjemplo2()
        );
        
        /*
            condicionar la respuesta de los metodos de la capa Repository
            a fines del test ya que al ser el obj productoRepository un Mock
            no va a interactuar con la BDD
        */
        when(productoRepository.findAll()).thenReturn(mockProductos); 

        List<Producto> resultado = productoService.obtenerTodosLosProductos();

        assertEquals(2, resultado.size());
        
        /*
            verifica que se llamo a findAll() de productoRepository
        */
        verify(productoRepository).findAll();
    }

    
    @Test
    void testEncontrarPorIdExistente() {
        Producto producto = crearProductoEjemplo1();
        
        /*
            Optional es una clase static que sirve para interactuar con objs que
            pueden llegar a ser null, el proposito de su uso es evitar Null Pointer Exception
        */
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = productoService.encontrarPorId(1L);

        assertNotNull(resultado);
        assertEquals("agua mineral", resultado.getNombre());
        verify(productoRepository).findById(1L);
    }

    
    @Test
    void testEncontrarPorIdNoExistente() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        Producto resultado = productoService.encontrarPorId(99L);

        assertNull(resultado);
        verify(productoRepository).findById(99L);
    }

    
    @Test
    void testExistePorId() {
        when(productoRepository.existsById(1L)).thenReturn(true);

        boolean existe = productoService.existePorId(1L);

        assertTrue(existe);
        verify(productoRepository).existsById(1L);
    }

    
    @Test
    void testGuardarProducto() {
        Producto producto = crearProductoEjemplo1();

        productoService.guardarProducto(producto);

        verify(productoRepository).save(producto);
    }

    
    @Test
    void testEliminarProductoPorId() {
        productoService.eliminarProductoPorId(1L);

        verify(productoRepository).deleteById(1L);
    }

    
    @Test
    void testContarProductos() {
        when(productoRepository.count()).thenReturn(3L);

        long total = productoService.contarProductos();

        assertEquals(3L, total);
        verify(productoRepository).count();
    }

    
    @Test
    void testGuardarListaProductos() {
        List<Producto> productos = List.of(
                crearProductoEjemplo1(),
                crearProductoEjemplo2()
        );

        productoService.guardarListaProductos(productos);

        verify(productoRepository).saveAll(productos);
    }
}
