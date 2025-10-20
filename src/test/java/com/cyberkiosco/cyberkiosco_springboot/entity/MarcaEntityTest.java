package com.cyberkiosco.cyberkiosco_springboot.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MarcaEntityTest {
    public Marca createMarcaEj(){
        return new Marca(1l, "arcor", null);
    }

    @Test
    void testSetNombreConValorNulo(){
        Marca marca = createMarcaEj();
        assertThrows(IllegalArgumentException.class, () -> {
            marca.setNombre(null);
        });
    }
}
