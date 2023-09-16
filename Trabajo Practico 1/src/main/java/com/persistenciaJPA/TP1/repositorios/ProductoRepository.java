package com.persistenciaJPA.TP1.repositorios;

import com.persistenciaJPA.TP1.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
