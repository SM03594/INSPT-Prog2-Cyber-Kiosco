
package com.cyberkiosco.cyberkiosco_springboot.repository;

import com.cyberkiosco.cyberkiosco_springboot.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    
}
