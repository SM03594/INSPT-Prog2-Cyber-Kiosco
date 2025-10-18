package com.cyberkiosco.cyberkiosco_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberkiosco.cyberkiosco_springboot.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    
}
