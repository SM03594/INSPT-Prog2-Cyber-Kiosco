
package com.cyberkiosco.cyberkiosco_springboot.repository;

import com.cyberkiosco.cyberkiosco_springboot.entity.Categoria;
import com.cyberkiosco.cyberkiosco_springboot.entity.Marca;
import com.cyberkiosco.cyberkiosco_springboot.entity.Producto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    Page<Producto> findByMarca(Marca marca, Pageable pageable);
    Page<Producto> findByCategoria(Categoria categoria, Pageable pageable);
    List<Producto> findByNombreLikeIgnoreCase(String nombre);
    Page<Producto> findByNombreLikeIgnoreCase(String nombre, Pageable pageable);
    // Buscar todos los activos paginados (para el index)
    Page<Producto> findByActivoTrue(Pageable pageable);

    // Buscar por nombre y que esté activo
    Page<Producto> findByNombreLikeIgnoreCaseAndActivoTrue(String nombre, Pageable pageable);
    
    // Filtrar por Marca y Activo
    Page<Producto> findByMarcaAndActivoTrue(Marca marca, Pageable pageable);

    // Filtrar por Categoria y Activo
    Page<Producto> findByCategoriaAndActivoTrue(Categoria categoria, Pageable pageable);
    
}
