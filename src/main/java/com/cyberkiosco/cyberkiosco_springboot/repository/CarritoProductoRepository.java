
package com.cyberkiosco.cyberkiosco_springboot.repository;

import com.cyberkiosco.cyberkiosco_springboot.entity.CarritoProducto;
import com.cyberkiosco.cyberkiosco_springboot.entity.embeddable.CarritoProductoKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, CarritoProductoKey> {

    List<CarritoProducto> findByCarrito_Id(Long id_carrito);
    List<CarritoProducto> findByProducto_Id(Long id_producto);
    
    /*
        Se utilizan listas ya que al buscar por un id o el otro se pueden
        obtener varios resultados provenientes de varios registros distintos
        
        Cada uno sirve para cuando solo se cuenta con una de las keys
        
        Optional<CarritoProducto> findById(CarritoProductoKey id); ya viene por defecto
    */
}
