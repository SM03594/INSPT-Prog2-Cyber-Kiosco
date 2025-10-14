
package com.cyberkiosco.cyberkiosco_springboot.entity;

import com.cyberkiosco.cyberkiosco_springboot.entity.exceptions.StockInsuficienteException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class ProductoEntityTest {
    
    private Producto crearProductoEj() {
        return new Producto(
                1L,
                "caramelo",
                5,
                1.5,
                "img/caramelo.jpg",
                1,
                3
        );
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
            producto.setId_producto(-1);
        });
    }

    @Test
    void testSetIdProductoConValorCero() {
        Producto producto = crearProductoEj();

        assertDoesNotThrow(() -> {
            producto.setId_producto(0);
        });
    }

    @Test
    void testSetIdProductoConValorPositivo() {
        Producto producto = crearProductoEj();

        assertDoesNotThrow(() -> {
            producto.setId_producto(12345);
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
