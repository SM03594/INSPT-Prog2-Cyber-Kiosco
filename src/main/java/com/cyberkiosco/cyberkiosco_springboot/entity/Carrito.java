
package com.cyberkiosco.cyberkiosco_springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Getter
@NoArgsConstructor 
@AllArgsConstructor
public class Carrito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double precio_total;
    private LocalDateTime fecha_compra;
    //pendiente implementar usuario al que pertenece el carrito
    
    
    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("El id_carrito no puede ser menor a cero.");
        }
        this.id = id;
    }
    
    public void setPrecio_total (double precio_total) {
        if (precio_total < 0) {
            throw new IllegalArgumentException("El precio_total no puede ser menor a cero.");
        }
        this.precio_total = precio_total;
    }

    public void setFecha_compra(LocalDateTime fecha_compra) {
        if(fecha_compra == null) {
            throw new IllegalArgumentException("La fecha_compra no puede ser null.");
        }
        if (fecha_compra.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha_compra no puede ser en el futuro.");
        }
        this.fecha_compra = fecha_compra;
    }
    
}
