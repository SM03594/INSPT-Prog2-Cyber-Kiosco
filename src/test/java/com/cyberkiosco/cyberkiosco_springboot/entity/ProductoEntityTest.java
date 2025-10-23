
package com.cyberkiosco.cyberkiosco_springboot.entity;

import com.cyberkiosco.cyberkiosco_springboot.entity.exceptions.StockInsuficienteException;
import com.cyberkiosco.cyberkiosco_springboot.service.CategoriaService;
import com.cyberkiosco.cyberkiosco_springboot.service.MarcaService;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class ProductoEntityTest {
    
    @Autowired
    private MarcaService marcaService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    private Producto crearProductoEj() {
        Producto prodNuevo = new Producto();
        prodNuevo.setNombre("Agua Mineral Saborizada");
        prodNuevo.setStock(5);
        prodNuevo.setPrecio(15.0);
        prodNuevo.setImagen("img/aguaMineralSaborizada.jpg");
        prodNuevo.setMarca(marcaService.encontrarPorId(2L));
        prodNuevo.setCategoria(categoriaService.encontrarPorId(2L));
        
        return prodNuevo;
    }
    

    @Test
    void testSetNombreConValorNulo() {
        Producto producto = crearProductoEj();

        //Se usa una expresion lambda ya  que las alternativas son mas verbosas
        assertThrows(IllegalArgumentException.class, () -> {
            producto.setNombre(null);
        });
    }

    @Test
    void testSetNombreConCadenaVacia() {
        Producto producto = crearProductoEj();

        assertThrows(IllegalArgumentException.class, () -> {
            producto.setNombre("");
        });
    }

    @Test
    void testSetNombreConSoloEspacios() {
        Producto producto = crearProductoEj();

        assertThrows(IllegalArgumentException.class, () -> {
            producto.setNombre("   ");
        });
    }
    
    @Test
    void testSetIdProductoConValorNegativo() {
        Producto producto = crearProductoEj();

        assertThrows(IllegalArgumentException.class, () -> {
            producto.setId(-1);
        });
    }

    @Test
    void testSetIdProductoConValorCero() {
        Producto producto = crearProductoEj();

        assertDoesNotThrow(() -> {
            producto.setId(0);
        });
    }

    @Test
    void testSetIdProductoConValorPositivo() {
        Producto producto = crearProductoEj();

        assertDoesNotThrow(() -> {
            producto.setId(12345);
        });
    }
    
    @Test
    void testRestarStockConValorMayorAlStockDisponible() {
        Producto producto = crearProductoEj();
        
        assertThrows(StockInsuficienteException.class, () -> {
            producto.restarStock(7);
        });
    }
    
}
