package com.persistenciaJPA.TP1.repositorios;

import com.persistenciaJPA.TP1.entidades.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
}
