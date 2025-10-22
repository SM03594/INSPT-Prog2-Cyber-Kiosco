
package com.cyberkiosco.cyberkiosco_springboot.entity;

import com.cyberkiosco.cyberkiosco_springboot.entity.auxiliar.Validacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor 
@AllArgsConstructor
public class Perfil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long id;
    
    @Column(name = "nombre_perfil")
    private String nombre;
    
    private String foto;
    
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    
    public void setNombre(String nombre) {
        Validacion.validarString(nombre, "nombre_perfil");
        this.nombre = nombre;
    }

    public void setFoto(String foto) {
        Validacion.validarString(nombre, "foto");
        this.foto = foto;
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
        final Perfil other = (Perfil) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.foto, other.foto)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.usuario, other.usuario);
    }
    
    
}

