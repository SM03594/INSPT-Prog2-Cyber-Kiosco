
package com.cyberkiosco.cyberkiosco_springboot.entity;

import com.cyberkiosco.cyberkiosco_springboot.entity.exceptions.StockInsuficienteException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Getter
@NoArgsConstructor 
@AllArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;
    private String nombre;
    private int stock;
    private double precio;
    private String imagen;
    private int id_categoria;
    private int id_marca;
    //pendiente implementar categoria y marca correctamente con JPA
    
    
    public void setNombre(String nombre) {
        // .trim() elimina los espacios en blanco al principio y al final del string
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacio.");
        }
        this.nombre = nombre;
    }
    
    public void restarStock (int cantidad) {
        int stockRestante;
        
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a restar al stock debe ser mayor a cero.");
        } else {
            stockRestante = this.stock - cantidad;
            
            if(stockRestante < 0) {
                throw new StockInsuficienteException("No se puede restar una cantidad mayor al stock de la que este tiene.");
            }
        }
        
        this.stock = stockRestante;
    }
    
    public void agregarStock (int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a sumar al stock debe ser mayor a cero.");
        }
        this.stock += cantidad;
    }
    
    public void setStock (int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser menor a cero.");
        }
        this.stock = stock;
    }
    
    public void setPrecio (double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser menor a cero.");
        }
        this.precio = precio;
    }
    
    public void setImagen(String imagen) {
        if (imagen == null || imagen.trim().isEmpty()) {
            throw new IllegalArgumentException("El path a la imagen no puede estar vacio.");
        }
        this.imagen = imagen;
    }

    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("El id_producto no puede ser menor a cero.");
        }
        this.id = id;
    }
    
    public void setId_categoria(int id_categoria) {
        if (id_categoria < 0) {
            throw new IllegalArgumentException("El id_categoria no puede ser menor a cero.");
        }
        this.id_categoria = id_categoria;
    }

    public void setId_marca(int id_marca) {
        if (id_marca < 0) {
            throw new IllegalArgumentException("El id_marca no puede ser menor a cero.");
        }
        this.id_marca = id_marca;
    }
    
    @Override
    public String toString() {
        return "Producto{" + "id_producto=" + id + ", nombre=" + nombre + ", stock=" + stock + ", precio=" + precio + ", imagen=" + imagen + ", id_categoria=" + id_categoria + ", id_marca=" + id_marca + '}';
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
        final Producto other = (Producto) obj;
        if (this.stock != other.stock) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (this.id_categoria != other.id_categoria) {
            return false;
        }
        if (this.id_marca != other.id_marca) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.imagen, other.imagen)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    
}
