package com.persistenciaJPA.TP1.repositorios;

import com.persistenciaJPA.TP1.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
