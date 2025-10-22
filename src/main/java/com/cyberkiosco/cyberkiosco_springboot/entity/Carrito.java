
package com.cyberkiosco.cyberkiosco_springboot.entity;

import com.cyberkiosco.cyberkiosco_springboot.entity.auxiliar.Validacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;
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
    @Column(name = "id_carrito")
    private Long id;
    private double precio_total;
    private LocalDateTime fecha_compra;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    
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

    @Override
    public String toString() {
        return "Carrito{" + "id=" + id + ", precio_total=" + precio_total + ", fecha_compra=" + fecha_compra + '}';
    }

    public void setUsuario(Usuario usuario) {
        Validacion.validarNotNull(usuario, "usuario");
        this.usuario = usuario;
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
        final Carrito other = (Carrito) obj;
        if (Double.doubleToLongBits(this.precio_total) != Double.doubleToLongBits(other.precio_total)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fecha_compra, other.fecha_compra)) {
            return false;
        }
        return Objects.equals(this.usuario, other.usuario);
    }

   
}
