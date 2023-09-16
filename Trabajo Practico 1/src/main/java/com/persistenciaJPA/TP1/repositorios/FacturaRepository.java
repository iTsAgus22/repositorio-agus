package com.persistenciaJPA.TP1.repositorios;

import com.persistenciaJPA.TP1.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
