
package com.cyberkiosco.cyberkiosco_springboot.repository;

import com.cyberkiosco.cyberkiosco_springboot.entity.Perfil;
import com.cyberkiosco.cyberkiosco_springboot.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByUsuario(Usuario usuario);
}

