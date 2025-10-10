
package com.cyberkiosco.cyberkiosco_springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long id_producto;
    private String nombre;
    private int stock;
    private double precio;
    private String imagen;
    private int id_categoria;
    private int id_marca;
    
    
    public void setNombre(String nombre) {
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
                throw new IllegalArgumentException("No se puede restar una cantidad mayor al stock de la que este tiene.");
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
    
}
