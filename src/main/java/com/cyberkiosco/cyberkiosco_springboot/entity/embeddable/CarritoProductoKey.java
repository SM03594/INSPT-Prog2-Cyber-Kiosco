
package com.cyberkiosco.cyberkiosco_springboot.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Embeddable //avisa que atributos serán insertados dentro de otra entidad, en lugar de ser una entidad independiente con su propia tabla
@Getter
@NoArgsConstructor 
@AllArgsConstructor
public class CarritoProductoKey implements Serializable {
    
    @Column(name = "id_carrito")
    private Long id_carrito;
    
    @Column(name = "id_producto")
    private Long id_producto;
    
    
    public void setId_producto(long id_producto) {
        if (id_producto < 0) {
            throw new IllegalArgumentException("El id_producto no puede ser menor a cero.");
        }
        this.id_producto = id_producto;
    }
    
    public void setId_carrito(long id_carrito) {
        if (id_carrito < 0) {
            throw new IllegalArgumentException("El id_carrito no puede ser menor a cero.");
        }
        this.id_carrito = id_carrito;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id_producto);
        hash = 67 * hash + Objects.hashCode(this.id_carrito);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarritoProductoKey other = (CarritoProductoKey) obj;
        if (!Objects.equals(this.id_producto, other.id_producto)) {
            return false;
        }
        return Objects.equals(this.id_carrito, other.id_carrito);
    }
    
}
